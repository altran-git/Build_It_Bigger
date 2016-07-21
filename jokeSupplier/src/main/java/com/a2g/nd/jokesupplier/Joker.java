package com.a2g.nd.jokesupplier;

public class Joker {
  String [] jokeArray;

  public Joker() {
    jokeArray = new String[10];
    jokeArray[0] = "Ordinarily, staring is creepy. But if you spread your attention across many individuals, then it's just people watching.";
    jokeArray[1] = "Q: Why can't a blonde dial 911? A: She can't find the eleven.";
    jokeArray[2] = "Yo momma is so fat, I took a picture of her last Christmas and it's still printing.";
    jokeArray[3] = "Q: What did one cannibal say to the other while they were eating a clown? A: Does this taste funny to you?";
    jokeArray[4] = "Q: What did the DNA say to the other DNA? A: Do these genes make my butt look fat.";
    jokeArray[5] = "You may be a square, baby, but you're round in all the right places.";
    jokeArray[6] = "Q: What did the farmer use to make crop circles? A: A Protractor";
    jokeArray[7] = "The worst thing you can hear when you're wearing a bikini is \"Good for you!\"";
    jokeArray[8] = "Can a kangaroo jump higher than a house? Of course, a house doesn't jump at all.";
    jokeArray[9] = "Q: How do you find Will Smith in the snow? A: You look for his Fresh Prints";
  }

  public String getJoke(int num){
    return jokeArray[num%10];
  }
}
