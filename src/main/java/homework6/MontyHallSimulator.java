import java.util.*;

public class MontyHallSimulator {

    public static void main(String[] args) {
        final int TOTAL_RUNS = 1000;
        Random random = new Random();

        Map<Integer, SimulationResult> results = new HashMap<>();

        int winWithSwitch = 0;
        int winWithoutSwitch = 0;

        for (int i = 1; i <= TOTAL_RUNS; i++) {
            int winningDoor = random.nextInt(3);  // 0, 1, or 2
            int playerChoice = random.nextInt(3);

            // Host opens a goat door
            int hostOpens;
            do {
                hostOpens = random.nextInt(3);
            } while (hostOpens == winningDoor || hostOpens == playerChoice);

            // Player strategy: switch
            int switchedChoice = 3 - playerChoice - hostOpens;
            boolean isSwitch = i % 2 == 0; // switch on even steps

            boolean isWin = (isSwitch ? switchedChoice : playerChoice) == winningDoor;

            // Save result
            results.put(i, new SimulationResult(i, isWin, isSwitch));

            if (isSwitch) {
                if (isWin) winWithSwitch++;
            } else {
                if (isWin) winWithoutSwitch++;
            }
        }

        // Output results
        System.out.println("== Итоги симуляции Монти Холла ==");
        System.out.println("Всего игр: " + TOTAL_RUNS);
        System.out.println("Побед с изменением выбора: " + winWithSwitch);
        System.out.println("Побед без изменения выбора: " + winWithoutSwitch);
        System.out.println("Процент выигрышей при смене: " + (winWithSwitch * 100.0 / (TOTAL_RUNS / 2)) + "%");
        System.out.println("Процент выигрышей без смены: " + (winWithoutSwitch * 100.0 / (TOTAL_RUNS / 2)) + "%");
    }
}