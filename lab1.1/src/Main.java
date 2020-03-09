import java.util.Scanner;
public class Main {
    public static void createGraph(){

    }

    public static char[][] createMatrix(char[] Vn, char[] Vt, String[] P){
        char[][] matrix = new char[6][5];
        for (int i= 0 ; i< 6; i++){
            for (int j = 0; j< 5; j++){
                matrix[i][j] = '0';
            }
        }
        matrix[0][0] = ' ';
        for(int i = 1; i<5; i++){
            matrix[i][0] = Vn[i-1];
        }
        for(int j = 1; j<5;j++){
            matrix[0][j]=Vn[j-1];
        }
        matrix[5][0] = 'F';
        for(int i=0;i<7;i++){
            int n,m;
                switch (P[i].charAt(0)) {
                    case 'S':
                        n = 1;
                        break;
                    case 'A':
                        n = 2;
                        break;
                    case 'B':
                        n = 3;
                        break;
                    case 'C':
                        n = 4;
                        break;
                    default:
                        n = 0;
                }
            if(P[i].length() == 4){
                matrix[5][n] = P[i].charAt(3);
            } else if(P[i].length() == 5){
                switch (P[i].charAt(4)) {
                    case 'S':
                        m = 1;
                        break;
                    case 'A':
                        m = 2;
                        break;
                    case 'B':
                        m = 3;
                        break;
                    case 'C':
                        m = 4;
                        break;
                    default:
                        m = 0;
                }
                matrix[m][n] = P[i].charAt(3);
            }
        }
        return matrix;
    }

    public static boolean check(String str, char[][] matrix){
        int n = str.length();
        char[] s = new char[n];
        for(int i=0; i<n;i++){
            s[i] = str.charAt(i);
        }
        for (int i =0; i<n; i++){
            if(s[i] != 'a' && s[i]!= 'b' && s[i] != 'c'){
                return false;}
        }
        if(s[0] != 'b' || s[n-1] != 'b'){
            return false;
        } else { int m=1; int j = 2; int k;
            while (m<n){
                if(m==n-1) {
                    if(matrix[5][j] == s[m])
                        return true;
                    else {
//                        System.out.println("3");
                        return false;}
                }
                else{
                for(int i=1;i<5;i++){
                    if(matrix[i][j] == s[m]){
                       k = i; char a = matrix[k][0];
                       for(int h = 0; h<5;h++){
                           if(matrix[0][h] == a){
                               j = h; m++;
                               }
                           } break;
                       }
                    if(i == 4 && matrix[i][j] != s[m]){
                        return false;
                    }
                } }
            } }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] Vn = {'S','B','D'};
        char[] Vt = {'a','b','c'};
        String[] P = {"S->aB","S->bB","B->bD","D->b","D->aD","B->cB","B->aS"};
        char[][] matrix = createMatrix(Vn,Vt,P);
        System.out.println("NFA : ");
        for(int i = 0; i< 6; i++){
            for(int j = 0; j< 5; j++){
                System.out.print( matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.print("Does there exist string ");
        String str = scanner.nextLine();
        System.out.print("?\n" + check(str, matrix));
        createGraph();
    }
}
