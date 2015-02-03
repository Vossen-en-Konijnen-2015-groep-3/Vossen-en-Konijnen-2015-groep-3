package sounds;

import java.io.*;
import sun.audio.*;

/**
 * A simple class with a main method to play a sound when you push RELEASE HELL!
 * 
 * @author Jan-Bert, Marcel, Robert en Jan
 * @version 03-02-2015
 * 
 */
public class Ziekte
{

  public static void main(String[] args) throws Exception
  {
    // open the sound file as a Java input stream
    String soundFile = "/ziekte.wav";
    // find the file it needs to play, by getting the resource
    InputStream in = Ziekte.class.getResourceAsStream("/ziekte.wav");
 
    // create an audiostream from the inputstream
    AudioStream audioStream = new AudioStream(in);
 
    // play the audio clip with the audioplayer class
    AudioPlayer.player.start(audioStream);
  }
  
}