package AprendizajeLearning;

import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        int[] puntuacionObtenida = new int[2];
        int[] puntuacionesFinales = new int[2];
        boolean continuarJugando = true;
        instruccionesJuego();
        while (continuarJugando == true) {
            puntuacionObtenida = partidaTresRaya();
            if (puntuacionObtenida[0] == 1) puntuacionesFinales[0] = puntuacionesFinales[0] + 1;
            else if (puntuacionObtenida[0] == 2) puntuacionesFinales[1] = puntuacionesFinales[1] + 1;
            mostrarPuntuaciones(puntuacionesFinales);
            continuarJugando = preguntarContinuarJugando();
            System.out.println();
            System.out.println();
            System.out.println();
        }
        mostrarBarra();
        mostrarBarra();
        System.out.print("                                                                                      FIN DE JUEGO");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////SUBPROGRAMAS/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static int[] partidaTresRaya() {
        int[] turnosJugadores = {1, 2, 1, 2, 1, 2, 1, 2, 1};
        char[] simbolosAsignados = new char[2];
        char simbolosPanel[][] = new char[3][3];
        mostrarTablero(simbolosPanel);
        selectorSimbolo(simbolosAsignados);
        mostrarBarra();
        mostrarTablero(simbolosPanel);
        int[] resultadoPartida = new int[2];
        for (int i = 0; i < 9; i++) {
            colocarFicha(peticionJugador(turnosJugadores[i], simbolosAsignados), simbolosPanel, turnosJugadores[i], simbolosAsignados);
            mostrarBarra();
            if (i > 3) { //A partir de este punto hay suficientes simbolos como para poder comenzar a comprobar si hay ganador o no.
                if (comprobarResultados(simbolosPanel) == true) {
                    mostrarTablero(simbolosPanel);
                    System.out.println("Gana el jugador " + turnosJugadores[i] + ". Enhorabuena!!");
                    resultadoPartida[0] = turnosJugadores[i];
                    resultadoPartida[1] = i;
                    break;
                }
            }
            mostrarTablero(simbolosPanel);
        }
        if (comprobarResultados(simbolosPanel) == false) {
            System.out.println("Empate, probad otra vez!");
            resultadoPartida[0] = 0;
            resultadoPartida[1] = 0;
        }
        mostrarBarra();
        System.out.print("                                                                                    FIN DE PARTIDA");
        return resultadoPartida;
    }


    public static void instruccionesJuego() {
        System.out.println(" ______________________________________");
        System.out.println("|  TRES EN RAYA - Tajo Valley Edition  |");
        System.out.println("|======================================|__________________________________________________________");
        System.out.println("|                                                                                                 |");
        System.out.println("|   Este juego consiste en vencer a tu oponente tratando de ser el primero en          |   | X    |");
        System.out.println("|   crear una linea de tres simbolos seguidos de aquel que hayas seleccionado.      --- --- ---   |");
        System.out.println("|   Para ello, tendras que seleccionar cuando llegue tu turno que columna y fila     O | X | O    |");
        System.out.println("|   quieres marcar, procurando ser mas inteligente que tu adversario, y recuerda    --- --- ---   |");
        System.out.println("|   que cuantos menos movimientos necesites para ganar, mas puntuacion obtendras.    X | O | X    |");
        System.out.println("|                                                                                                 |");
        System.out.println("|   Buena suerte!!                                                                                |");
        System.out.println("|_________________________________________________________________________________________________|");
        System.out.println();
    }


    public static void selectorSimbolo(char[] simbolosAsignados) {
        System.out.print("Jugador 1 - Por favor, indica si deseas jugar con X u O: ");
        Scanner sc = new Scanner(System.in);
        char valorJugador1 = sc.next().charAt(0);
        char valorJugador2 = ' ';
        if (valorJugador1 == 'X' || valorJugador1 == 'x') {
            valorJugador1 = 'X';
            valorJugador2 = 'O';
            simbolosAsignados[0] = valorJugador1;
            simbolosAsignados[1] = valorJugador2;
        } else if (valorJugador1 == 'O' || valorJugador1 == 'o') {
            valorJugador1 = 'O';
            valorJugador2 = 'X';
            simbolosAsignados[0] = valorJugador1;
            simbolosAsignados[1] = valorJugador2;
        } else if (esSimboloInvalido(valorJugador1)) {
            System.out.println(" ///ERROR/// Por favor, selecciona uno de los dos valores mencionados.");
            selectorSimbolo(simbolosAsignados);
        }
        if (!esSimboloInvalido(valorJugador1)) {
            System.out.println();
            System.out.println("El jugador 1 ha elegido " + valorJugador1 + ", el jugador 2 por tanto jugara con " + valorJugador2 + ".");
            simbolosAsignados[0] = valorJugador1;
            simbolosAsignados[1] = valorJugador2;
        }
    }


    public static boolean esSimboloInvalido(char valorJugador1) {
        if (valorJugador1 != 'X' && valorJugador1 != 'x' && valorJugador1 != 'O' && valorJugador1 != 'o') return true;
        else return false;
    }


    public static void mostrarTablero(char[][] simbolosPanel) {
        System.out.println();
        System.out.println("    " + simbolosPanel[0][0] + " | " + simbolosPanel[0][1] + " | " + simbolosPanel[0][2] + "    ");
        System.out.println("   --- --- ---   ");
        System.out.println("    " + simbolosPanel[1][0] + " | " + simbolosPanel[1][1] + " | " + simbolosPanel[1][2] + "    ");
        System.out.println("   --- --- ---   ");
        System.out.println("    " + simbolosPanel[2][0] + " | " + simbolosPanel[2][1] + " | " + simbolosPanel[2][2] + "    ");
        System.out.println();
    }


    public static char[] peticionJugador(int jugador1, char[] simbolosAsignados) {
        char[] letraYsimbolo = new char[3];
        if (jugador1 == 1) letraYsimbolo[0] = simbolosAsignados[0];
        else letraYsimbolo[0] = simbolosAsignados[1];
        System.out.print("Jugador " + jugador1 + ", introduce el numero de columna de la ubicacion de tablero elegida: ");
        Scanner sc = new Scanner(System.in);
        char columna = sc.next().charAt(0);
        while (columna != '1' && columna != '2' && columna != '3') {
            System.out.print(" ///ERROR/// Por favor " + jugador1 + ", introduce un numero de columna valido (entre 1 y 3): ");
            Scanner st = new Scanner(System.in);
            columna = st.next().charAt(0);
            if (columna == '1' || columna == '2' || columna == '3') break;
        }
        letraYsimbolo[1] = columna;
        System.out.print("Jugador " + jugador1 + ", introduce el numero de  fila de la ubicacion de tablero elegida: ");
        Scanner so = new Scanner(System.in);
        char fila = so.next().charAt(0);
        while (fila != '1' && fila != '2' && fila != '3') {
            System.out.print(" ///ERROR/// Por favor " + jugador1 + ", introduce un numero de fila valido (entre 1 y 3): ");
            Scanner sp = new Scanner(System.in);
            fila = sp.next().charAt(0);
            if (fila == '1' || fila == '2' || fila == '3') break;
        }
        letraYsimbolo[2] = fila;
        return letraYsimbolo;
    }


    public static void colocarFicha(char[] letraYsimbolo, char[][] simbolosPanel, int jugador1, char[] simbolosAsignados) {
        if (((simbolosPanel[Character.getNumericValue(letraYsimbolo[2]) - 1][Character.getNumericValue(letraYsimbolo[1]) - 1]) == 'X') || ((simbolosPanel[Character.getNumericValue(letraYsimbolo[2]) - 1][Character.getNumericValue(letraYsimbolo[1]) - 1]) == 'O')) {
            while (((simbolosPanel[Character.getNumericValue(letraYsimbolo[2]) - 1][Character.getNumericValue(letraYsimbolo[1]) - 1]) == 'X') || ((simbolosPanel[Character.getNumericValue(letraYsimbolo[2]) - 1][Character.getNumericValue(letraYsimbolo[1]) - 1]) == 'O')) {
                System.out.println(" ///ERROR/// Casilla ocupada, por favor jugador " + jugador1 + ", introduce de nuevo una ubicacion: ");
                char[] numerosTMP = peticionJugador(jugador1, simbolosAsignados);
                if (((simbolosPanel[Character.getNumericValue(numerosTMP[2]) - 1][Character.getNumericValue(numerosTMP[1]) - 1]) != 'X') && ((simbolosPanel[Character.getNumericValue(numerosTMP[2]) - 1][Character.getNumericValue(numerosTMP[1]) - 1]) != 'O')) {
                    simbolosPanel[Character.getNumericValue(numerosTMP[2]) - 1][Character.getNumericValue(numerosTMP[1]) - 1] = numerosTMP[0];
                    break;
                }
            }
        } else
            simbolosPanel[Character.getNumericValue(letraYsimbolo[2]) - 1][Character.getNumericValue(letraYsimbolo[1]) - 1] = letraYsimbolo[0];
    }


    public static void mostrarBarra() {
        System.out.println("__________________________________________________________________________________________________");
    }


    public static boolean comprobarResultados(char[][] simbolosPanel) {
        if (esVertical(simbolosPanel) == true || esHorizontal(simbolosPanel) == true || esOblicua(simbolosPanel) == true)
            return true;
        else return false;
    }


    public static boolean esVertical(char[][] simbolosPanel) {
        char simbolo;
        int contadorCoincidentes = 0;
        for (int columna = 0; columna < simbolosPanel.length; columna++) {
            contadorCoincidentes = 0;
            simbolo = simbolosPanel[0][columna];
            for (int fila = 0; fila < simbolosPanel.length; fila++) {
                if (((simbolo == 'X') || (simbolo == 'O')) && (simbolo == simbolosPanel[fila][columna]))
                    contadorCoincidentes++;
                if (contadorCoincidentes == simbolosPanel.length) {
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean esHorizontal(char[][] simbolosPanel) {
        char simbolo;
        int contadorCoincidentes = 0;
        for (int fila = 0; fila < simbolosPanel.length; fila++) {
            contadorCoincidentes = 0;
            simbolo = simbolosPanel[fila][0];
            for (int columna = 0; columna < simbolosPanel.length; columna++) {
                if (((simbolo == 'X') || (simbolo == 'O')) && (simbolo == simbolosPanel[fila][columna]))
                    contadorCoincidentes++;
                if (contadorCoincidentes == simbolosPanel.length) {
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean esOblicua(char[][] simbolosPanel) {
        char limiteSupIzq = simbolosPanel[0][0];
        int contadorOblIzq = 0;
        for (int i = 0; i < simbolosPanel.length; i++) {
            if (((limiteSupIzq == 'X') || (limiteSupIzq == 'O')) && (limiteSupIzq == simbolosPanel[i][i]))
                contadorOblIzq++;
            if (contadorOblIzq == simbolosPanel.length) {
                return true;
            }
        }
        char limiteSupDcha = simbolosPanel[0][simbolosPanel.length - 1];
        int contadorOblDcha = 0;
        for (int i = 0; i < simbolosPanel.length; i++) {
            if (((limiteSupDcha == 'X') || (limiteSupDcha == 'O')) && (limiteSupDcha == simbolosPanel[i][(simbolosPanel.length - i) - 1]))
                contadorOblDcha++;
            if (contadorOblDcha == simbolosPanel.length) {
                return true;
            }
        }
        return false;
    }


    public static void mostrarPuntuaciones(int[] puntuacionesFinales) {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("                           ===================================");
        System.out.println("                                 MARCADORES DE VICTORIAS      ");
        System.out.println("                                                              ");
        System.out.println("                            Jugador 1              Jugador 2  ");
        System.out.println("                              " + puntuacionesFinales[0] + "                      " + puntuacionesFinales[1]);
        System.out.println("                           ===================================");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }


    public static boolean preguntarContinuarJugando() {
        System.out.print("Deseas iniciar una nueva partida? S/N: ");
        Scanner cn = new Scanner(System.in);
        char decisionChar = cn.next().charAt(0);
        boolean decisionTMP = false;
        if (decisionChar == 'S' || decisionChar == 's') return true;
        else if (decisionChar == 'N' || decisionChar == 'n') return false;
        else if (decisionChar!='S' && decisionChar!='s'&& decisionChar!='N' && decisionChar!='n') {
            System.out.println(" ///ERROR/// Valor introducido erroneo, repita por favor.");
            if (!preguntarContinuarJugando()) decisionTMP = false;
            else decisionTMP = true;
        }
        return decisionTMP;
    }

}
