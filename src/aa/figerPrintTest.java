package aa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.attilax.img.imgx;

import sourceafis.simple.AfisEngine;
import sourceafis.simple.Fingerprint;
import sourceafis.simple.Person;

public class figerPrintTest {
	
//	public static void main(String[] args) {
//		
//	}

	// Initialize path to images
	static String ImagePath = Path.Combine("D:\\0workspace\\atiplat_img\\figerprint", "images");

	// Shared AfisEngine instance (cannot be shared between different threads
	// though)
	static AfisEngine Afis;

	// Take fingerprint image file and create Person object from the image
	static Person Enroll(String filename, int name) {
		Console.WriteLine("Enrolling {0}...", String.valueOf(name));

		// Initialize empty fingerprint object and set properties
		Fingerprint fp = new Fingerprint();

		// fp.Filename = filename;
		// Load image from the file
		Console.WriteLine(" Loading image from {0}...", filename);
		// BitmapImage image = new BitmapImage(new Uri(filename,
		// UriKind.RelativeOrAbsolute));
		// fp.AsBitmapSource = image;
		byte[][] byteArr = imgx.toByteArr(filename);
		System.out.println("--");
		fp.setImage(byteArr);
		// Above update of fp.AsBitmapSource initialized also raw image in
		// fp.Image
		// Check raw image dimensions, Y axis is first, X axis is second
		// Console.WriteLine(" Image size = {0} x {1} (width x height)",
		// fp.getImage(), fp.Image.GetLength(0));

		// Initialize empty person object and set its properties
		Person person = new Person();
		// person.set
		person.setId(name);
		;
		// Add fingerprint to the person
		person.getFingerprints().add(fp);

		// Execute extraction in order to initialize fp.Template
		Console.WriteLine(" Extracting template...");
		Afis.extract(person);
		// Check template size
		Console.WriteLine(" Template size = {0} bytes", fp.getTemplate().length);

		return person;
	}

	public static void main(String[] args) {
		// Initialize SourceAFIS
		Afis = new AfisEngine();

		// Enroll some people
		List<Person> database = new ArrayList<Person>();
		database.add(Enroll(Path.Combine(ImagePath, "candidate1.tif"), 1));
		database.add(Enroll(Path.Combine(ImagePath, "candidate2.tif"), 2));
		database.add(Enroll(Path.Combine(ImagePath, "candidate3.tif"), 3));
		//
		// // Save the database to disk and load it back, just to try out the
		// serialization
		// BinaryFormatter formatter = new BinaryFormatter();
		// Console.WriteLine("Saving database...");
		// Stream stream = File.Open("database.dat", FileMode.Create);
		// formatter.Serialize(stream, database);
		// Console.WriteLine("Reloading database...");
		// FileStream stream = File.OpenRead("database.dat")
		// database = (List<MyPerson>)formatter.Deserialize(stream);

		// Enroll visitor with unknown identity
		String figerImg = Path.Combine(ImagePath, "probe.tif");
		int tmpPersonId = 9;
		Person probe = Enroll(figerImg, tmpPersonId);

		// Look up the probe using Threshold = 10
		Afis.setThreshold(10);
		// Afis.identify(arg0, arg1)
		Iterable<Person> identify = Afis.identify(probe, database);
		// Person match = identify;
		// Null result means that there is no candidate with similarity score
		// above threshold
		Person match = null;
		for (Person match_person : identify) {
			match = match_person;
			// Compute similarity score
			float score = Afis.verify(probe, match_person);
			Console.WriteLine("Similarity score between {0} and {1} = {2}", probe.getId(), match_person.getId(), score);

		}
		if (match == null) {
			Console.WriteLine("No matching person found.");
			return;
		}
		// Print out any non-null result
		// Console.WriteLine("Probe {0} matches registered person {1}",
		// probe.Name, match.Name);

	}
}
