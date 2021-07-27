import java.util.*;
import java.awt.event.*;
public class TwentyFortyEight {
    private int[][] game;
    private int score;

    //Creates a new 2048 game
    public void createGame(){
        score=0;
        Scanner gameSize=new Scanner(System.in);
        boolean validInput1=false;
        int n=0;
        //Continues to recieve an input for grid size from user until a valid input is entered
        while(validInput1==false){
            System.out.println("Enter the size of grid (greater than 1)");
            n=gameSize.nextInt();
        if(n>1)
        validInput1=true;
        else{
            validInput1=false;
            System.out.println("The grid size must be greater than 1");
        }
        }
        game=new int[n][n];
        //Sets the value of each tile to 0 initially
        for(int i=0;i<game.length;i++){
            for(int j=0;j<game[i].length;j++){
                game[i][j]=0;
            }
        }
        int count=0;
        while(count<2){
        int a=(int)(Math.random()*game.length);
        int b=(int)(Math.random()*game[0].length);
        if(game[a][b]==0){
            int p=(int)(Math.random()*100);
            if(p<19)
            game[a][b]=4;
            else
            game[a][b]=2;
            count++;
        }
    }
    this.printGame();
    boolean gameOngoing=true;
    String input="";
    Scanner sc=new Scanner(System.in);
    //Continues to take input from user and move tiles accordingly until game is over
    while(gameOngoing==true){
        boolean validInput2=false;
        while(validInput2==false){
        System.out.println("Enter direction(\"w\",\"a\",\"s\",\"d\") or quit(\"q\")");
        input=sc.nextLine();
        if(input.equals("w")){
            validInput2=true;
            this.moveUp();
  }
        else if(input.equals("s")){
            validInput2=true;
            this.moveDown();
        }
        else if(input.equals("a")){
            validInput2=true;
            this.moveLeft();
        }
        else if(input.equals("d")){
            validInput2=true;
            this.moveRight();
        }
        else if(input.equals("q")){
            validInput2=true;
            this.printGame();
            this.printScore();
            gameOngoing=false;
        }
        else{
            System.out.println("Invalid input. Please enter appropraite input.");
            validInput2=false;
        }
        }
    int count1=0;
    //Checks if any tile is vacant
    for(int i=0;i<game.length;i++){
        for(int j=0;j<game[i].length;j++){
            if(game[i][j]!=0)
            count1++;
        }
    }
    //If no vacant tile is available checks if no tiles are combinable
    if(count1==(game.length)*(game[0].length)){
        int count2=0;
        int count3=0;
        for(int i=0;i<game.length;i++){
            for(int j=0;j<game[i].length-1;j++){
                if(game[i][j]==game[i][j+1]){
                    count2++;
                }
            }
        }
        for(int i=0;i<game.length-1;i++){
            for(int j=0;j<game[0].length;j++){
                if(game[i][j]==game[i+1][j]){
                    count3++;
                }
            }
        }
        if(count2==0 && count3==0){
            System.out.println("Game Over");
            gameOngoing=false;
        }
    }
}
}

    //Prints the game for player to view
    public void printGame(){
        for(int i=0;i<game[0].length;i++){
            for(int j=0;j<game.length;j++){
                System.out.print(" ___ ");
            }
            System.out.println();
            for(int j=0;j<game.length;j++){
                System.out.print("| "+game[j][i]+" |");
               }
               System.out.println();
        }
    }

    //Moves the tiles upward
    public void moveUp(){
        //Goes through each tile column by column starting from the first tile in each column
        for(int i=0;i<game.length;i++){
            for(int j=0;j<game[i].length;j++){
                int val=0;
                //Checks to see if a tile does not value 0 to move it appropriately
                if(game[i][j]!=0)
                {
                    int k=j+1;
                    //Compares a tile to every other tile below it in the column it belongs to
                    while(k<game[i].length){
                        //Checks to see if tiles having different values separarte two tiles having the same value
                        if(game[i][k]!=0 && game[i][k]!=game[i][j]){
                        val=game[i][j];
                        game[i][j]=0;
                        break;
                        }
                        //Checks if two adjacent tiles in a column have the same value
                        else if(game[i][j]==game[i][k]){
                            val=game[i][j]+game[i][k];
                            score+=val;
                            game[i][j]=game[i][k]=0;
                            break;
                        }
                        else
                        k++;
                    }
                    //Checks if a tile is the only tile having a particular value in the column it belongs to
                    if(k==game[i].length){
                        val=game[i][j];
                        game[i][j]=0;
                    }
                    for(int l=0;l<game[i].length;l++){
                        if(game[i][l]==0){
                            game[i][l]=val;
                            break;
                        }
                    }
                }
            }
        }
        this.newTile();
        this.printGame();
        this.printScore();
    }

    //moves the tiles downwards
    public void moveDown(){
        //Goes through each tile column by column starting from the last tile in each column
        for(int i=(game.length)-1;i>=0;i--){
            for(int j=(game[i].length)-1;j>=0;j--){
                int val=0;
                //Checks to see if a tile does not have the value 0 to move it appropriately
                if(game[i][j]!=0)
                {
                    int k=j-1;
                    //Compares a particular tile to every other tile above it in the column it belongs to
                    while(k>-1){
                        //Checks to see if tiles having different values separate two tiles having the same value
                         if(game[i][k]!=0 && game[i][k]!=game[i][j]){
                            val=game[i][j];
                            game[i][j]=0;
                            break;
                            }
                        //Checks to see if two adjacent tiles have the same value in a particular column
                        else if(game[i][j]==game[i][k]){
                            val=game[i][j]+game[i][k];
                            score+=val;
                            game[i][j]=game[i][k]=0;
                            break;
                        }
                        else
                        k--;
                    }
                    //Checks if a tile is the only tile having a particular value in the column it belongs to
                    if(k==-1){
                        val=game[i][j];
                        game[i][j]=0;
                    }
                    for(int l=(game[i].length)-1;l>=0;l--){
                        if(game[i][l]==0){
                            game[i][l]=val;
                            break;
                        }
                    }
                }
            }
        }
        this.newTile();
        this.printGame();
        this.printScore();

    }

    //moves the tiles towards the left
    public void moveLeft(){
        //Goes through every tile row by row starting from the first tile in each row
        for(int i=0;i<game[0].length;i++){
            for(int j=0;j<game.length;j++){
                int val=0;
                //Checks to see if a tile does not have the value 0 to move it appropriately
                if(game[j][i]!=0){
                int k=j+1;
                //Compares a tile to every other tile that follows it in the row it belongs to
                while(k<game.length){
                    //Checks if tiles having different values separate two tiles having the same value
                    if(game[k][i]!=0 && game[k][i]!=game[j][i]){
                        val=game[j][i];
                        game[j][i]=0;
                        break;
                        }
                    //Checks if two adjacent tiles in a row have the same value
                    else if(game[j][i]==game[k][i]){
                        val=game[j][i]+game[k][i];
                        score+=val;
                        game[j][i]=game[k][i]=0;
                        break;
                    }
                    else
                    k++;
                }
                //Checks if a tile is the only tile having a particular value in the row it belongs to
                if(k==game.length){
                    val=game[j][i];
                    game[j][i]=0;
                }
              for(int l=0;l<game.length;l++){
                    if(game[l][i]==0){
                        game[l][i]=val;
                        break;
                    }
                }
            }
            }
        }
        this.newTile();
        this.printGame();
        this.printScore();
    }

    //moves the tiles towards the right
    public void moveRight(){
        //Goes through every tile starting from the last tile in each row
        for(int i=(game[0].length)-1;i>=0;i--){
            for(int j=(game.length)-1;j>=0;j--){
                int val=0;
                //Checks if a tile does not have a value of 0 to move it appropriately
                if(game[j][i]!=0){
                int k=j-1;
                //Compares a tile to every other tile that precedes it in the row it belongs to
                while(k>-1){
                    //Checks if tiles having different values separate two tiles having the same value
                    if(game[k][i]!=0 && game[k][i]!=game[j][i]){
                        val=game[j][i];
                        game[j][i]=0;
                        break;
                        }
                    //Checks if two adjacent tiles in a row have the same value
                    else if(game[j][i]==game[k][i]){
                        val=game[j][i]+game[k][i];
                        score+=val;
                        game[j][i]=game[k][i]=0;
                        break;
                    }
                    else
                    k--;
                }
                //Checks if a tile is the only tile having a particular value in the row it belongs to
                if(k==-1){
                    val=game[j][i];
                    game[j][i]=0;
                }
              for(int l=(game.length)-1;l>=0;l--){
                    if(game[l][i]==0){
                        game[l][i]=val;
                        break;
                    }
                }
            }
            }
        }
        this.newTile();
        this.printGame();
        this.printScore();
    }

    //generates a new tile with a value of either 2 or 4
    public void newTile(){
        boolean vacantPos=false;
        //Code continues until the value of a vacant tile to 2 or 4
        while(vacantPos==false){
            int a=(int)(Math.random()*game.length);
            int b=(int)(Math.random()*game[a].length);
            //Sets the value of a vacant tile to either 2 or 4 
            if(game[a][b]==0){
                int c=(int)(Math.random()*100);
                if(c<19)
                game[a][b]=4;
                else
                game[a][b]=2;
                vacantPos=true;
            }
        }
    }

    //prints the player's score
    public void printScore(){
        System.out.println("Score: "+score);
    }

    public static void main(String[] args) {
        TwentyFortyEight ob=new TwentyFortyEight();
        ob.createGame();
    }
}