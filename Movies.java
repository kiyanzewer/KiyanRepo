import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Movies {
	
	public static void main(String [] args)
	{
		Gson og = new GsonBuilder().setPrettyPrinting().create();
		String s = null;
		try 
		{
			s = getFileContents("./src/moviesJson.json");
		} 
		catch (IOException e1) {
		}
		MoviesJSON[] movies = og.fromJson( s, MoviesJSON[].class);
		Scanner scan = new Scanner(System.in);
		System.out.println("Please select an option by typing a number:\n1. Look up an Actor/Actress \n2. Look up a Movie Title \n3. Look up a Year \n4. Add a movie \n5. Degrees to Kevin Bacon");
		int num = Integer.parseInt(scan.nextLine());
		String choice = "Your choice was: " + num;
		if(num == 1)
		{
			choice = "You chose 1. Look up an Actor/Actress";
			System.out.println(choice);
			boolean contains = false;
			String name = scan.nextLine();
			for(int i = 0; i < movies.length; i++)
			{
				String [] cast = movies[i].getCast();
				for(int x = 0; x < cast.length; x++)
				{
					if(cast[x].equals(name))
					{
						contains = true;
						System.out.println(movies[i].getTitle());
					}
				}
			}
			if(contains == false)
			{
				System.out.println("This person does not exist. Please run the program again and retry");
			}
			System.out.println("\n\nIf you would like to return to the main menu type menu");
			String menu = scan.nextLine();
			if(menu.equals("menu"))
			{
				main(args);
			}
		}
		else if(num == 2)
		{
			choice = "You chose 2. Look up a Movie Title";
			System.out.println(choice);
			String title = scan.nextLine();
			int counter = 0;
			boolean contains = false;
			List<MoviesJSON> moviesSel = new ArrayList<MoviesJSON>();
			for(int i = 0; i < movies.length; i++)
			{
				if(title.equals(movies[i].getTitle()))
				{
					contains = true;
					counter++;
					moviesSel.add(movies[i]);
					System.out.println(counter + ". " + title + "( " + movies[i].getYear() + ", " + movies[i].getCast()[0] + " )");
				}
			}
			if(contains == false)
			{
				System.out.println("This movie does not exist. Please re-run the program and try again");
			}
			else
			{
				System.out.println("If you want to know more about these above films then type the number that is next to it or type menu to go to the menu");
				String next = scan.nextLine();
				if(next.equals("menu"))
				{
					main(args);
				}
				System.out.println(moviesSel.get(Integer.parseInt(next)-1));
			}
			System.out.println("\n\nIf you would like to return to the main menu type menu");
			String menu = scan.nextLine();
			if(menu.equals("menu"))
			{
				main(args);
			}
		}
		else if(num == 3)
		{
			choice = "You chose 3. Look up a Year";
			System.out.println(choice);
			int year = Integer.parseInt(scan.nextLine());
			int counter = 0;
			for(int i = 0; i < movies.length; i++)
			{
				if (movies[i].getYear() == year)
				{
					counter++;
				}
			}
			System.out.println("There were " + counter + " movies made this year");
			System.out.println("\n\nIf you would like to return to the main menu type menu");
			String menu = scan.nextLine();
			if(menu.equals("menu"))
			{
				main(args);
			}
		}
		else if(num == 4)
		{
			choice = "You chose 4. Add a movie";
			System.out.println(choice);
			System.out.println("Please type in a title");
			String title = scan.nextLine();
			System.out.println("Please type in the year it came out");
			int year = Integer.parseInt(scan.nextLine());
			System.out.println("Please type the cast members, separate each by only a comma followed by a space");
			String cast = scan.nextLine();
			String [] casts = cast.split(", ");
			System.out.println("Please type in the genre, separate each by only a comma follwed by a space");
			String genre = scan.nextLine();
			String[] genres = genre.split(", ");
			MoviesJSON [] mov = new MoviesJSON[movies.length + 1];
			MoviesJSON m = new MoviesJSON();
			m.setTitle(title);
			m.setYear(year);
			m.setCast(casts);
			m.setGenres(genres);
			for(int i = 0; i < mov.length - 1; i++)
			{
				mov[i] = movies[i];
			}
			mov[movies.length] = m;
			String json = og.toJson(mov);
			try {
				writeFileContents("./src/moviesJson.json", json);
			} catch (IOException e) {
				
			}
			if(mov.length > movies.length)
			{
				System.out.println("Added!");
			}
			System.out.println("\n\nIf you would like to return to the main menu type menu");
			String menu = scan.nextLine();
			if(menu.equals("menu"))
			{
				main(args);
			}
		}
		else if(num == 5)
		{
			choice = "You chose 5. Degree to Kevin Bacon";
			System.out.println(choice);
			System.out.println("\n\nIf you would like to return to the main menu type menu");
			String menu = scan.nextLine();
			if(menu.equals("menu"))
			{
				main(args);
			}
		}
		else
		{
			choice = "Sorry, you did not type a valid choice";
			System.out.println(choice);
		}
		scan.close();
	}
	
	private static String getFileContents( String filename ) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filename)));
    }
	
	private static void writeFileContents( String fileName, String data ) throws IOException
	{
		FileWriter fw = new FileWriter( new File(fileName) );
		fw.write(data);
		fw.close();
	}

}
