import java.util.Random;

public class Main {
    public static int[] heroesHealth = {270, 280, 250,200};
    public static int[] heroesDamage = {20, 15, 25,0};
    public static String[] heroesAttackType = {"Physical",
            "Magical", "Kinetic", "Medic"};
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int round_number = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void round() {
        round_number++;
        bossDefenceType = changeBossDefence();
        System.out.println("Boss choose: " + bossDefenceType);
        bossHits();
        medic();
        heroesHit();
        printStatistics();
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static String changeBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(
                heroesAttackType.length); // 0,1,2
        return heroesAttackType[randomIndex];
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                heroesHealth[i] = heroesHealth[i] - bossDamage;
                if (heroesHealth[i] < 0) {
                    heroesHealth[i] = 0;
                }
            }
        }
    }

    public static void heroesHit() {
        Random random = new Random();
        int coeff = random.nextInt(8) + 2; //2,3,4,5,6,7,8,9
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i] == bossDefenceType) {
                    bossHealth = bossHealth - heroesDamage[i] * coeff;
                    System.out.println("Critical damage: ["
                            + coeff + "] = " +
                            heroesDamage[i] * coeff);
                } else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
                if (bossHealth < 0) {
                    bossHealth = 0;
                }
            }
        }
    }

    public static void medic() {
        //проверка жив ли медик if 
            // random
            // создаете переменную int min, int index, k = random... создаете рандомную переменную для лечения
            // создаете цикл
                //  min = heroesHealth[i],
                // ставите условие нахождения минимального значения здоровья
                    // index = i
                    // min = ..
            // проверка жив ли герой min > 0
                // heroesHealth[index] += k;
                // sout медик вылечил heroesAttacksType[index] на k



    }

    public static void printStatistics() {
        System.out.println("________ ROUND [" + round_number + "]");
        System.out.println("Boss health: " + bossHealth
                + " [" + bossDamage + "]");
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i] +
                    " health: " + heroesHealth[i] + " [" +
                    heroesDamage[i] + "]");
        }
        System.out.println("________________");
    }
}
