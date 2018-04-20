/*
    Jack Defay
    Neuron.java
    4/19/2018
*/

import java.util.Random;

public class Neuron {

        public double[] synapticWeights;

        private Random rand;

        private double output;
        private double[][] error;
        private double[][] adjustment;

            public Neuron(){
            this.rand = new Random(1);
            this.synapticWeights = new double[4];
            for(int i = 0; i<4; i++) {
                synapticWeights[i] = (rand.nextInt(2));
            }
            this.synapticWeights = this.add(synapticWeights, 1);
            this.synapticWeights = this.multiply(synapticWeights, 2);
            this.synapticWeights = this.subtract(synapticWeights, 1);
        }

        private double sigmoid(double x){
            return (1/(1+Math.exp(-x)));
        }

        private double sigmoidDerivative(double x){
            return (x*(1-x));
        }

        public double think(double[][] inputs){
            return this.sigmoid(this.dot(inputs, this.synapticWeights));
        }

        public void train(double[][] trainingSetInputs, double[][] trainingSetOutputs, int numberOfTrainingIterations){
            for(int i = 0; i<numberOfTrainingIterations; i++){
                output = this.think(trainingSetInputs);
                error = this.subtract(trainingSetOutputs, output);
                this.adjustment = this.matrixMultiply(this.transpose(trainingSetInputs), this.multiply(error, this.sigmoidDerivative(this.output)));
                this.synapticWeights = this.matrixAdd(this.synapticWeights, this.adjustment);
            }
        }

        private static double dot(double[][] matrix1, double[] matrix2){
            double sum = 0;
            for(int a = 0; a<matrix2.length; a++){
                for(int b = 0; b<matrix1[0].length; b++){
                        sum += matrix1[b][a] * matrix2[a];
                }
            }
            return sum;
        }

        public static double[][] transpose(double[][] matrix){
            double[][] answer = new double[matrix[0].length][matrix.length];
            for(int i = 0; i < matrix.length; i++) {
                for(int j = i+1; j < matrix[0].length; j++) {
                    answer[i][j] = matrix[j][i];
                }
            }

            return answer;
        }

        private static double[][] matrixMultiply(double[][] matrix1, double[][] matrix2){
            double[][] answer = new double[matrix1.length][matrix2.length];
            double sum = 0;

            for(int a = 0; a<matrix1[0].length; a++){
                for(int b = 0; b<matrix2.length; b++){
                    for(int c = 0; c<matrix1.length; c++){
                        sum += matrix1[c][a] * matrix2[b][c];
                    }
                    answer[a][b] = sum;
                }
            }

            return answer;

        }

        private static double[][] matrixAdd(double[][] matrix1, double[][] matrix2){
                double[][] answer = new double[matrix1.length][matrix1[0].length];

                for(int i = 0; i<matrix1.length; i++){
                    for(int j = 0; j<matrix1[0].length; j++){
                        answer[i][j] = matrix1[i][j] + matrix2[i][j];
                    }
                }
                return answer;
        }

        private static double[][] multiply(double[][] matrix, double scalar){
            double[][] answer = new double[matrix.length][matrix[0].length];
            for(int i = 0; i<matrix.length; i++){
                for(int j = 0; j<matrix[0].length; j++){
                    answer[i][j] = matrix[i][j] * scalar;
                }
            }

            return answer;
        }

        private static double[][] subtract(double[][] matrix, double scaler){
            double[][] answer = new double[matrix.length][matrix[0].length];
            for(int i = 0; i<matrix.length; i++){
                for(int j = 0; j<matrix[0].length; j++){
                    answer[i][j] = matrix[i][j] - scaler;
                }
            }

            return answer;
        }

        private static double[][] add(double[][] matrix, double scaler){
            double[][] answer = new double[matrix.length][matrix[0].length];
            for(int i = 0; i<matrix.length; i++){
                for(int j = 0; j<matrix[0].length; j++){
                    answer[i][j] = matrix[i][j] + scaler;
                }
            }

            return answer;
        }

        private static double[] add(double[] matrix, double scaler){
            double[] answer = new double[matrix.length];
            for(int i = 0; i<matrix.length; i++){
                    answer[i] = matrix[i] + scaler;
            }

            return answer;
        }

        private static double[] multiply(double[] matrix, double scalar){
            double[] answer = new double[matrix.length];
            for(int i = 0; i<matrix.length; i++){
                    answer[i] = matrix[i] * scalar;
            }

            return answer;
        }

        private static double[] subtract(double[] matrix, double scaler){
            double[] answer = new double[matrix.length];
            for(int i = 0; i<matrix.length; i++){
                    answer[i] = matrix[i] - scaler;
            }

            return answer;
        }
    }


