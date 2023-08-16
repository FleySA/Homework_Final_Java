import java.util.Random;
import java.util.Scanner;

class MathQuiz {
    private static final int NUM_QUESTIONS = 6;
    private static final int MAX_POINTS = 5;
    private Random random;
    private Scanner scanner;

    public MathQuiz() {
        random = new Random();
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.print("Введите количество игроков: ");
        int numPlayers = scanner.nextInt();

        String[] playerNames = new String[numPlayers]; // Массив для хранения имен игроков
        int[] scores = new int[numPlayers]; // Массив для хранения счетов игроков
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Введите имя игрока " + (i + 1) + ": ");
            playerNames[i] = scanner.next();

            // Вызов метода игры и сохранение результата в массив счетов
            scores[i] = playGame(playerNames[i], i + 1);
        }
// Вызов метода для сортировки и вывода результатов
        sortScoresAndPrintResults(playerNames, scores);
    }

    private int playGame(String playerName, int playerNumbers) {
        System.out.println("\n Игрок " + playerName);
        int score = 0;

        for (int i = 0; i < NUM_QUESTIONS; i++) {
            score += askQuestion();
        }

        return score;
    }

    private int askQuestion() {
        int num1, num2, correctAnswer, userAnswer, operator;
        int attempts = 1;
        int questionScore = 0;

        num1 = random.nextInt(100);
        num2 = random.nextInt(20);
        operator = random.nextInt(4); // 0: -, 1: +, 2: /, 3: *

        switch (operator) {
            case 0:
                correctAnswer = num1 - num2;
                break;
            case 1:
                correctAnswer = num1 + num2;
                break;
            case 2:
                correctAnswer = num1 / num2;
                break;
            case 3:
                correctAnswer = num1 * num2;
                break;
            case 4:
                correctAnswer = num1 - num2;
                break;
            case 5:
                correctAnswer = num1 + num2;
                break;
            default:
                throw new IllegalStateException("Некорректный оператор");
        }

        for (int i = 0; i < attempts; i++) {
            System.out.println("Первое число: " + num1);
            System.out.println("Второе число: " + num2);

            if (operator == 0) {
                System.out.print("Введите результат вычитания этих чисел: ");
            } else if (operator == 1) {
                System.out.print("Введите сумму из этих чисел: ");
            } else if (operator == 2) {
                System.out.print("Введите результат деления этих чисел: ");
            } else if (operator == 3) {
                System.out.print("Введите произведение этих чисел: ");
            }   else if (operator == 4) {
                System.out.print("Введите результат вычитания этих чисел: ");
            } else if (operator == 5) {
                System.out.print("Введите сумму из этих чисел: ");
            }
            userAnswer = scanner.nextInt();

            if (userAnswer == correctAnswer) {
                System.out.println("Вы дали правильный ответ и заработали " + MAX_POINTS + " баллов");
                questionScore += MAX_POINTS;
                break;
            } else {
                System.out.println("Вы дали неправильный ответ и заработали " + (-MAX_POINTS) + " баллов");
                questionScore -= MAX_POINTS;
            }
        }

        return questionScore;
    }

    private void sortScoresAndPrintResults(String[] playerNames, int[] scores) {
        for (int i = 0; i < playerNames.length - 1; i++) {
            for (int j = 0; j < playerNames.length - i - 1; j++) {
                
                // Если счет текущего игрока (j) меньше счета следующего игрока (j + 1), меняем их местами
                if (scores[j] < scores[j + 1]) {
                    // Перестановка счетов игроков
                    int tempScore = scores[j];
                    scores[j] = scores[j + 1];
                    scores[j + 1] = tempScore;

                // Перестановка местами имен игроков
                    String tempName = playerNames[j];
                    playerNames[j] = playerNames[j + 1];
                    playerNames[j + 1] = tempName;

                }
            }
        }
// После сортировки выводим результаты для каждого игрока в убывающем порядке баллов
        for (int i = 0; i < playerNames.length; i++) {
            printFinalScore(playerNames[i], scores[i]);
        }
    }

    private void printFinalScore(String playerName, int score) {
        System.out.println(playerName + ", ваш финальный счет: " + score + " баллов");
    }
}

