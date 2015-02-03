package sounds;

import java.io.*;
import sun.audio.*;

/**
 * A simple class with a main method to play a sound for tree fall. 
 * 
 * @author Jan-Bert, Marcel, Robert en Jan
 * @version 02-02-2015
 * 
 */
public class Boom
{

  public static void main(String[] args) throws Exception
  {
    // open the sound file as a Java input stream
    String soundFile = "/boom.wav";
    // find the file it needs to play, by getting the resource
    InputStream in = Boom.class.getResourceAsStream("/boom.wav");
 
    // create an audiostream from the inputstream
    AudioStream audioStream = new AudioStream(in);
 
    // play the audio clip with the audioplayer class
    AudioPlayer.player.start(audioStream);
  }
  
}