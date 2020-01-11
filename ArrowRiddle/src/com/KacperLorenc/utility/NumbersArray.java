package com.KacperLorenc.utility;

public class NumbersArray {
    private int[][] tab;
    private int length;

    public NumbersArray(int length) {
        this.length = length;
        tab = new int[length][length];
    }
    public int getIntAt(int i, int j){
        return tab[i][j];
    }
    public void setIntAt(int i, int j, int integer){
        if (i>= 0 && i < this.length && j>=0 && j<this.length )
            tab[i][j] = integer;
    }

    public void populate(ArrowArray up, ArrowArray down, ArrowArray left, ArrowArray right){

     if(up.getLength() != this.length || down.getLength() != this.length ||
        left.getLength()!=this.length || right.getLength()!= this.length)
          return;

        for (int i = 0; i<up.getLength(); i++){
                char arrow = up.getCharAt(i);
                if (arrow == '/' || arrow == 92)
                    upAddAtAnAngle(i,arrow);
                else
                    addInColumn(i);
        }
        for (int i = 0; i<down.getLength(); i++) {
            char arrow = down.getCharAt(i);
            if(arrow == '/' || arrow == 92)
                downAddAtAnAngle(i,arrow);
            else
                addInColumn(i);
        }
        for (int i = 0; i<left.getLength(); i++) {
            char arrow = left.getCharAt(i);
            if(arrow == '/' || arrow == 92)
                leftAddAtAnAngle(i,arrow);
            else
                addInRow(i);
        }
        for (int i = 0; i<right.getLength(); i++) {
            char arrow = right.getCharAt(i);
            if(arrow == '/' || arrow == 92)
                rightAddAtAnAngle(i,arrow);
            else
                addInRow(i);
        }


    }
    private void addInRow(int index){
        for (int i = 0; i < this.length; i++){
            tab[i][index]+=1;
        }
    }
    private void addInColumn(int index){
        for (int i = 0; i < this.length; i++){
            tab[index][i]+=1;
        }
    }
    private void upAddAtAnAngle(int index, char arrow){
        int j = 0;
        if(arrow == '/'){
            for (int i = index-1; i>=0; i--){
                tab[i][j++]+=1;
            }
        }
        else if(arrow == 92){  // '\'
            for (int i = index+1; i<this.length; i++){
                tab[i][j++]+=1;
            }
        }
    }
    private void downAddAtAnAngle(int index, char arrow){
        int j = this.length -1;
        if(arrow == 92){  // '\'
            for (int i = index-1; i>=0; i--){
                tab[i][j--]+=1;
            }
        }
        else if(arrow == '/'){
            for (int i = index+1; i<this.length; i++){
                tab[i][j--]+=1;
            }
        }
    }
    private void leftAddAtAnAngle(int index, char arrow){
        int j = 0;
        if(arrow == '/'){
            for (int i = index-1; i>=0; i--){
                tab[j++][i]+=1;
            }
        }
        else if(arrow == 92){  // '\'
            for (int i = index+1; i<this.length; i++){
                tab[j++][i]+=1;
            }
        }
    }
    private void rightAddAtAnAngle(int index, char arrow){
        int j = this.length-1;
        if(arrow == '/'){
            for (int i = index+1; i<this.length; i++){
                tab[j--][i]+=1;
            }
        }
        else if(arrow == 92){  // '\'
            for (int i = index-1; i>=0; i--){
                tab[j--][i]+=1;
            }
        }
    }
}
