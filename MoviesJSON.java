import java.util.Arrays;

public class MoviesJSON {

	private String title;
	private int year;
	private String [] cast;
	private String [] genres;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String[] getGenres() {
		return genres;
	}
	public void setGenres(String[] genres) {
		this.genres = genres;
	}
	public String[] getCast() {
		return cast;
	}
	public void setCast(String[] cast) {
		this.cast = cast;
	}
	public String toString()
	{
		return "\nTitle: " + getTitle() + "\nYear: " + getYear() + "\nGenres: " + Arrays.toString(getGenres()) + "\nCast: " + Arrays.toString(getCast());
	}
}
