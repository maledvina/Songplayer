import java.util.*;
/** file: Melody.java
 * plays music*/

public class Melody{
	private Queue<Note> song;
	
	
	/*Initializes your melody to store the passed in Queue of Notes. */
	public Melody(Queue<Note> song){
		this.song = song;
	}
	
	/*Returns the total length of the song in seconds. 
	If the song includes a repeated section the length 
	should include that repeated section twice.
	*/
	public double getTotalDuration(){
		Queue<Note> copySong = new LinkedList<Note>();
		double result = 0;
		boolean repeat = false;
		while(!song.isEmpty()){
			Note note = song.remove();
			copySong.add(note);
			result = result + note.getDuration();
			
			if(note.isRepeat() || repeat){
			result+= note.getDuration(); 
			if (note.isRepeat() == true && repeat == true){
				repeat = false;
			}else{
				repeat = true;
			}
			}
		} 
		song=copySong;
		return result;
				
			
				
	
	}
	/*Returns a String containing information about each note. 
	Each note should be on its own line and output using its toString method.
	*/
	public String toString(){
		Queue<Note> copySong = new LinkedList<Note>();
		String result = "";
		while(!song.isEmpty()){
			Note note = song.remove();
			copySong.add(note);
			result = result + note.toString()+"\n";
		}
		song = copySong;
		return result;
	
	}
	/*Changes the tempo of each note to be tempo percent of what it formerly was. 
	Passing a tempo of 1.0 will make the tempo stay the same.  
	tempo of 2.0 will make each note twice as long. 
	tempo of 0.5 will make each note half as long. 
	Keep in mind that when the tempo changes the length of the song also changes.
	*/
	public void changeTempo(double tempo){
		Queue<Note> copySong = new LinkedList<Note>();
		while(!song.isEmpty()){
			Note note = song.remove();
			note.setDuration(tempo * note.getDuration());
			copySong.add(note);
		}
		song = copySong;
		
	}
	/*Reverses the order of notes in the song, so that future calls to the 
	play methods will play the notes in the opposite of the order they were 
	in before reverse was called.  For example, a song containing notes 
	A, F, G, then B would become B, G, F, A. 
	You may use one temporary Stack or one temporary Queue to help you solve this problem.
	*/
	public void reverse(){
		Stack<Note> copySong = new Stack<Note>();
		while(!song.isEmpty()){
			Note note = song.remove();
			copySong.push(note);
		}
		while(!copySong.isEmpty()){
			Note note = copySong.pop();
			song.add(note);
		}

	}

	/*Adds all notes from the given other song to the end of this song. 
	For example, if this song is A,F,G,B and the other song is F, C, D, 
	your method should change this song to be A, F, G, B, F, C, D.  
	The other song should be unchanged after the call. 
	Remember that objects can access the private fields of other objects of the same type. 
	*/
	public void append(Melody other){
		
		while(!other.song.isEmpty()){
			Note note = other.song.remove();
			song.add(note);
		
		}
	
	}
	
	/*Plays the song by calling each note's play method. 
	The notes should be played from the beginning of the queue to the end 
	unless there are notes that are marked as being the beginning or end 
	of a repeated section. When the first note that is a beginning or end 
	of a repeated section is found you should create a second queue. 
	You should then get notes from the original queue until you see another
	marked as being the beginning or end of a repeat. 
	As you get these notes you should play them and then place them back in both queues. 
	Once you hit a second marked as beginning or end of a repeat you should play 
	everything in your secondary queue and then return to playing from the main queue. 
	It should be possible to call this method multiple times and get the same result.
	*/
	public void play(){
		Queue<Note> copySong = new LinkedList<Note>();
		boolean repeats = false;
		Queue<Note> repeat = new LinkedList<Note>();
	
		
		while(!song.isEmpty()){
			Note note = song.remove();
			copySong.add(note);
			note.play();
			if(note.isRepeat() || repeats){
				repeat.add(note);
			if (note.isRepeat() == true && repeats == true){ // end repeat
				repeats = false;
				while(!repeat.isEmpty()){
					Note noteRepeat = repeat.remove();
					noteRepeat.play();
				}	
			}else{
				repeats = true;
			}
			}
		} 
		
		
		
	}
	

}
