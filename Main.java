package project_self.InverseMatrix;

import java.util.Scanner;

public class Main {

    /*
    三种初等变换
    1.对调两行
    2.非零常数乘某行
    3.乘k加到另一行列
     */

    public static void ExchangeTwo(double[][] Matrix, int a,int b,char RC){
        double[] temp = new double[Matrix[a-1].length];
        if(RC == 'r'){
            for (int i = 0; i < Matrix[a-1].length; i++) {
                temp[i] = Matrix[a-1][i];
                Matrix[a-1][i] = Matrix[b-1][i];
                Matrix[b -1][i] = temp[i];
            }
        }else {
            for (int i = 0; i < Matrix.length; i++) {
                temp[i] = Matrix[i][a-1];
                Matrix[i][a-1] = Matrix[i][b-1];
                Matrix[i][b-1] = temp[i];
            }
        }
    }

    public static void MultiNum(double[][] Matrix,int a,double b,char RC){
        if(RC == 'r'){
            for (int i = 0; i < Matrix[a-1].length; i++) {
                Matrix[a-1][i] = Matrix[a-1][i]*b;
            }
        }else {
            for (int i = 0; i < Matrix.length; i++) {
                Matrix[i][a-1] = Matrix[i][a-1]*b;
            }
        }
    }

    public static void MutiNumRC(double[][] Matrix,double k,int a,int b,char RC){
        if(RC == 'r'){
            for (int i = 0; i < Matrix[a-1].length; i++) {
                Matrix[a-1][i] = Matrix[a-1][i]+Matrix[b-1][i]*k;
            }
        }else {
            for (int i = 0; i < Matrix.length; i++) {
                Matrix[i][a-1] = Matrix[i][a-1]+Matrix[i][a-1]*k;
            }
        }
    }

    public static double[][] Get(double[][] Matrix){
        double[][] IdentityMatrix = GetIdentityMatrix(Matrix.length);
        if(checkMatrix(Matrix)){
            exchange(Matrix);
        }
        for (int i = 1; i <= Matrix.length; i++) {
            for (int j = i+1; j <= Matrix.length; j++) {
                System.out.println("r"+j+(-(Matrix[j-1][i-1]/Matrix[i-1][i-1]))+"r"+i);
                MutiNumRC(IdentityMatrix,-(Matrix[j-1][i-1]/Matrix[i-1][i-1]),j,i,'r');
                MutiNumRC(Matrix,-(Matrix[j-1][i-1]/Matrix[i-1][i-1]),j,i,'r');
            }
        }//下三角
        for (int i = Matrix.length; i >=1; i--) {
            for (int j = i-1; j>=1; j--) {
                MutiNumRC(IdentityMatrix,-(Matrix[j-1][i-1]/Matrix[i-1][i-1]),j,i,'r');
                MutiNumRC(Matrix,-(Matrix[j-1][i-1]/Matrix[i-1][i-1]),j,i,'r');
            }
        }
        for (double[] matrix : Matrix) {
            for (int j = 0; j < Matrix.length; j++) {
                if (!(matrix[j] >= 0 || matrix[j] < 0)) {
                    System.out.println("不可逆");
                    return null;
                }
            }
        }
        if(checkIdentity(Matrix)){
            return IdentityMatrix;
        }else {
            for (int i = 1; i <= Matrix.length; i++) {
                MultiNum(IdentityMatrix,i,1/Matrix[i-1][i-1],'r');
                MultiNum(Matrix,i,1/Matrix[i-1][i-1],'r');
            }
            return IdentityMatrix;
        }
    }

    public static boolean checkIdentity(double[][] Matrix){
        boolean ans = true;
        for (int i = 0; i < Matrix.length; i++) {
            for (int j = 0; j < Matrix.length; j++) {
                if (!((i == j && Matrix[i][j] == 1) || Matrix[i][j] == 0)){
                    ans = false;
                    break;
                }
            }
        }
        return ans;
    }

    public static double[][] GetIdentityMatrix(int n){//得到n阶单位矩阵
        double[][] Arr = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i==j){
                    Arr[i][j] = 1;
                }else {
                    Arr[i][j] = 0;
                }
            }
        }
        return Arr;
    }

    public static double[][] input(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("n*n矩阵的n值");
        int n = scanner.nextInt();
        double[][] arr = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = scanner.nextDouble();
            }
        }
        return arr;
    }

    public static void printArr(double[][] arr){
        for (double[] ints : arr) {
            for (int j = 0; j < arr.length; j++) {
                System.out.printf("%.2f\t",ints[j]);
            }
            System.out.println();
        }
    }

    public static boolean checkMatrix(double[][] Matrix){
        for (int i = 0; i < Matrix.length; i++) {
            for (int j = 0; j < Matrix.length; j++) {
                if(i==j&&Matrix[i][j]==0){
                    return true;
                }
            }
        }
        return false;
    }

    public static void exchange(double[][] Matrix){
        for (int i = 0; i < Matrix.length; i++) {
            if(Matrix[i][i]==0){
                for (int j = i; j < Matrix.length; j++) {
                    if(Matrix[j][i]!=0&&Matrix[j][j]!=0){
                        ExchangeTwo(Matrix,j,i,'c');
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        double[][] Matrix = input();
        double[][] ans = Get(Matrix);
        if(ans!=null){
            printArr(ans);
        }else {
            System.out.println("结束了");
        }
    }
}