import java.util.Scanner;

public class MazeRunner {

    public static Scanner input = new Scanner(System.in);
    public static Maze myMap = new Maze();

   public static void main(String[] args) {
        intro();
        int count = 0;

        while (myMap.didIWin() == false) {

            String direction = userMove();

            if (direction.equals("R") && myMap.canIMoveRight() == true) {
                myMap.moveRight();
            }
            else if (direction.equals("L") && myMap.canIMoveLeft() == true) {
                myMap.moveLeft();
            }
            else if (direction.equals("U") && myMap.canIMoveUp() == true) {
                myMap.moveUp();
            }
            else if (direction.equals("D") && myMap.canIMoveDown() == true){
                myMap.moveDown();
            }
            else if (myMap.isThereAPit(direction)) {
                navigatePit(direction);
            }
            else {
                System.out.println("Sorry, you’ve hit a wall.");
            }

            myMap.printMap();

            count++;
            movesMessage(count);
            if (count == 100) {
                System.out.println("Sorry, but you didn't escape in time- you lose!");
                break;
            }

        }
        if (myMap.didIWin() == true && count < 100)
            System.out.println("Congratulations, you made it out alive!" + "and you did it in " + count + " moves");

    }
    // Welcome message
    public static void intro() {
        System.out.println("Welcome to Maze Runner!\nHere is your current position:");
        myMap.printMap();
    }
    // Asking for user input on direction to move
    public static String userMove() {
        System.out.println("Where would you like to move? (R, L, U, D) ");
        String direction = input.next();
        while (!(direction.equals("R") | direction.equals("L") | direction.equals("U") | direction.equals("D")))
            direction = input.next();
        return direction;
    }
    // Move limit
    public static void movesMessage(int count) {
        if (count == 50)
            System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes");
        if (count == 75)
            System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
        if (count == 90)
            System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!");
        if (count == 100)
            System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[");
    }
    // Check if there are pits ahead
    public static void navigatePit(String direction) {
        Boolean X = myMap.isThereAPit(direction); // Takes in the direction String the user entered in and returns if there is a pit ahead
        if (X == true) {
            System.out.println("Watch out! There's a pit ahead, jump it?");
            String jump = input.next();
            if (jump.startsWith("y"))
                myMap.jumpOverPit(direction); // Will jump over a pit in the direction given, skipping that space and landing 2 spaces over in the direction specified.
        }

    }
}
