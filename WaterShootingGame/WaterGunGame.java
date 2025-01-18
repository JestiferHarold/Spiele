import java.util.Scanner;
import java.util.Random;

class HealthBar{

	private int HP;
	private Random randomiser;

	private boolean isPlayerDead(){
		if (this.HP <= 0) {
			return true;
		} 
		return false;
	}

	public void damageInflicted(int damage, Object self) {
		this.HP -= damage;
		self.attackSuccessful(damage);
	}
	
	public void healDamage() {
		java.util.function.Supplier<Integer> heal = () -> {
			int rand = randomiser.nextInt(20);
			if (this.HP + rand > 100) {
				return 100 - this.HP;
			}
			return rand;
		};
		this.HP += heal;
	}

//256 for white	

	public HealthBar() {
		this.HP = 1000;
		randomiser = new Random();
	}
}

class Testing{
	public static void main(String[] args){
		HealthBar healthing = new HealthBar();
		System.out.println(healthing.healDamage());
	}
}

class WaterGun{
	private int gunPower;
	private Random randomiser;

	public void fillGun() {
		this.gunPower += randomiser.nextInt(1, 21);
	}

	public void attackSuccessfull(int damage) {
		this.gunPower -=  damage;
	}

	
	public void attack(Object Health) {
		Health.(randomiser.nextInt(1, fullPower));
	}

	public WaterGun() {
		this.gunPower = 0;
	}
}

public class WaterGunGame{
	
	private static Random randomiser;

	public boolean missedAttack(){
		return randomiser.nextBoolean() && randomiser.nextBoolean() || randomiser.nextBoolean() && randomiser.nextBoolean();
	}

	public static void main(String[] args) throws InterruptedException{

		Scanner prompt = new Scanner(System.in);
		randomiser = new Random();
		WaterGunGame gunner = new WaterGunGame();

		System.out.print("\033\143");
		System.out.println("WELCOME TO WATER GUN GAME");
		System.out.println("Enter the name of the first player : ");
		String firstPlayer = prompt.nextLine();
		System.out.println("Enter the name of the second player : ");
		String secondPlayer = prompt.nextLine();
		System.out.print("\033\143");
		Thread.sleep(1000);
		
		int firstPlayerHP = 100,
		    secondPlayerHP = 100,
		    gun1Power = 0,
		    gun2Power = 0;

		boolean isPlayer1Turn = true;

		while (firstPlayerHP > 0 && secondPlayerHP > 0) {

			System.out.println(firstPlayer + "'s water gun is " + gun1Power + "% filled and will cause " + gun1Power + " damage to the enemy if used");
			System.out.println("\n" + secondPlayer + "'s water gun is " + gun2Power + "% filled and will cause " + gun2Power + " damage to the enemy if used");
			if (isPlayer1Turn) {
				System.out.println("\n" + firstPlayer + ", Choose your option (1. Fill your gun, 2. Attack " + secondPlayer + ")");
			} else {
				System.out.println("\n" + secondPlayer + ", Choose your option (1. Fill your gun, 2. Attack " + firstPlayer + ")");
			}

			int action = prompt.nextInt();

			switch (action) {

				case 1:
					if (isPlayer1Turn) {
						if (gun1Power >= 100) {
							System.out.println("Gun is fully filled try again");
							continue;
						}
						System.out.print("\033\143");
						System.out.println(firstPlayer + " has filled their gun");
						gun1Power += gunner.fillGun();
						
					} else {
						if (gun2Power >= 100) {
							System.out.println("Gun is fully filled try again");
							continue;
						}
						System.out.print("\033\143");
						System.out.println(secondPlayer + " has filled their gun");
						gun2Power += gunner.fillGun();
					}
					break;

				case 2:
					if (isPlayer1Turn) { 
						if (gun1Power == 0) {
							System.out.print("\033\143");
							System.out.println("UhOh " + firstPlayer + "'s gun has no water in it");
							break;
						}
						if (!gunner.missedAttack()) {
							System.out.print("\033\143");
							System.out.println(firstPlayer + "'s attack has been missed");
							break;
						}
	
						int drained = gunner.attack(gun1Power);	
						secondPlayerHP -= drained;
						gun1Power -= drained;

						System.out.println("Hooray!!!! Attack successfull " + firstPlayer + " has inflicted " + secondPlayer + " " + drained + " damage and now " + secondPlayer + " has " + secondPlayerHP + " HP");
					} else {
						if (gun2Power == 0) {
							System.out.println("UhOh " + secondPlayer + "'s gun has no water in it");
							break;
						}
						if (!gunner.missedAttack()) {
							System.out.println(secondPlayer + "'s attack has been missed");
							break;
						}
	
						int drained = gunner.attack(gun2Power);	
						firstPlayerHP -= drained;
						gun2Power -= drained;

						System.out.println("Hooray!!!! Attack successfull " + secondPlayer + " has inflicted " + firstPlayer + " " + drained + " damage and now " + firstPlayer + " has " + firstPlayerHP + " HP");
					}
					
					break;

				default:

					System.out.println("Invalid option try again");
					continue;

			}
			
			isPlayer1Turn = !isPlayer1Turn;
		}
	}
}

