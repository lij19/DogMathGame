//CIS254 - Midterm Project - Dog-sitting Planner Math Game
//Select a dog's profile and input answers to play the game
//Displays a dog's care plan when all answers are correct
//Author: Jennifer Li
//Date: 10/17/2023

import java.util.Scanner;
import java.math.BigDecimal; //import library for floating point arithmetic
import java.math.RoundingMode; //import library for scaling and rounding 

public class DogMathGame {

	public static void main(String[] args) {
		//declare intro variables 
		@SuppressWarnings("resource") //removes warning for scnr
		Scanner scnr = new Scanner(System.in);
		String username;
		String playAgainYesNo = "yes";
		
		//prompt username
		System.out.println("What is your name? (Enter a name)");
		username = scnr.nextLine();
		System.out.println();
		
		// welcome message and game introduction
		System.out.printf("Welcome, %s, to your Doggy Daycare Planner!\n", username);
		System.out.println("You just opened up a new doggy daycare center. Congratulations!");
		System.out.println("Five furry friends are excited to spend the day in your care.");
		System.out.println("You want to make a great first impression to your first clients, ");
		System.out.println("so you decide to create and deliver a care plan customized to each dog's needs.\n");
		System.out.println("This planner is designed to help you make the best customized plan for each dog.");
		System.out.println("However, you will be required to use your math skills to determine the ");
		System.out.println("appropriate care for each dog.\n");
		System.out.println("Take out a pen and paper, and get ready to calculate!");
		System.out.println("The success of your new business depends on it!\n");
		
		while (playAgainYesNo.toUpperCase().equals("YES")) {
				
			// declare global variables and set arrays
			String[] dogName = { "Bingo", "Clifford", "Goofy", "Snoopy", "Tiny" };
			int[] dogAge = { 5, 7, 12, 10, 3 };
			String[] dogSize = { "medium", "large", "medium", "small", "large" };
			String[] dogColor = { "tan", "red", "black", "white", "gray" };
			int selectDog;
			int chosenDogIndex;
			String chosenDogSize;
			String chosenDogColor;
			int chosenDogColorLength;

			// declare task one variables
			int digestiveKibblePercent = 0, heartKibblePercent = 0;
			int gramsFood = 0;
			int userDigestiveWeight = 0, userHeartWeight = 0;
			boolean digestFoodAnswer = false, heartFoodAnswer = false;
			boolean correctNumInput = false;

			// declare task two variables
			int chosenDogAge;
			int walkTime = 0;
			double walkPace = 0;
			double userWalkDistance = 0;
			boolean walkDistanceAnswer = false;
			boolean correctNumInput2 = false;

			// declare task three variables and set arrays
			String[] addServices = { "Wash", "Nail Trim", "Mini Photoshoot", "Home Pickup or Dropoff" };
			int[] addServicesPrices = { 40, 18, 50, 70 };
			int[] budget = { 100, 200, 50, 120, 150 };
			int i;
			int firstServiceIndex = 0, secondServiceIndex = 0;
			String userServicesYesNo;
			int firstServicePrice, secondServicePrice;
			int dogBudget;
			boolean budgetCompare;

			// declare ending variables
			String userServicesYesNoCompare;

			// display menu of dog clients
			// prompt select dog
			System.out.println("Here's the list of your first clients: ");
			System.out.println("1 - Bingo");
			System.out.println("2 - Clifford");
			System.out.println("3 - Goofy");
			System.out.println("4 - Snoopy");
			System.out.println("5 - Tiny");
			System.out.print("\nInput the number next to a client's name to begin a plan: ");
			selectDog = scnr.nextInt();

			// error message and reprompt if no number from menu selected
			while (selectDog < 1 || selectDog > 5) {
				System.out.println("No client selected.");
				System.out.print("Please input a number from the client list: ");
				selectDog = scnr.nextInt();
			}

			chosenDogIndex = selectDog - 1; // adjust menu number to index value
			System.out.printf("Let's start a care plan for %s.\n\n", dogName[chosenDogIndex]);

			// display first task: how much food to feed dog (in grams)
			System.out.printf("Your first task is to find out how much food %s will need to eat.\n",
					dogName[chosenDogIndex]);
			System.out.printf("Here's the diet requirements for %s:\n", dogName[chosenDogIndex]);

			// choose display weight of food based on size
			chosenDogSize = dogSize[chosenDogIndex];
			switch (chosenDogSize) {
			case "small":
				gramsFood = 200;
				break;
			case "medium":
				gramsFood = 400;
				break;
			case "large":
				gramsFood = 700;
				break;
			}

			// choose display ratio for dog based on color
			chosenDogColor = dogColor[chosenDogIndex];
			chosenDogColorLength = chosenDogColor.length();
			switch (chosenDogColorLength) {
			case 3:
				digestiveKibblePercent = 70;
				heartKibblePercent = 30;
				break;
			case 4:
				digestiveKibblePercent = 45;
				heartKibblePercent = 55;
				break;
			case 5:
				digestiveKibblePercent = 20;
				heartKibblePercent = 80;
				break;
			}

			// display food requirements
			System.out.printf("- Total weight of food per day: %d grams\n", gramsFood);
			System.out.printf("- Ratio of kibble: %d%% DIGESTIVE health kibble and %d%% HEART health kibble\n",
					digestiveKibblePercent, heartKibblePercent);
			System.out.printf("How much each of DIGESTIVE health kibble and HEART health kibble should %s be fed?\n\n", dogName[chosenDogIndex]);

			// validate input is number
			// catch exceptions thrown for input values that are not numbers
			while (correctNumInput == false) {
				try {
					System.out.print("Enter DIGESTIVE health kibble weight in grams: ");
					userDigestiveWeight = scnr.nextInt();
					System.out.print("Enter HEART health kibble weight in grams: ");
					userHeartWeight = scnr.nextInt();
					correctNumInput = true;
				} catch (Exception e) { // if not number then display error message
					System.out.println("Oops! Try again and enter a valid number.");
					scnr.next(); // clears scanner object and prevents error message looping
				}
			}

			// call isDigestFoodWeightValid to validate digestive kibble answer
			// call isHeartFoodWeightValid to validate heart health kibble answer
			digestFoodAnswer = isDigestFoodWeightValid(userDigestiveWeight, digestiveKibblePercent, gramsFood);
			heartFoodAnswer = isHeartFoodWeightValid(userHeartWeight, heartKibblePercent, gramsFood);
			// prompt for input again if an answer is incorrect
			// catch exceptions thrown for non-number values
			while (digestFoodAnswer != true || heartFoodAnswer != true) {
				try {
					System.out.println("Hm, not quite. Try again! You got this!");
					if (digestFoodAnswer != true) {
						System.out.println("Tip: Percentages represent a fraction of 100. (e.g. 70% of 100 grams is 70 grams.)");
						System.out.print("Enter DIGESTIVE health kibble weight in grams: ");
						userDigestiveWeight = scnr.nextInt();
						digestFoodAnswer = isDigestFoodWeightValid(userDigestiveWeight, digestiveKibblePercent,
								gramsFood);
					}

					if (heartFoodAnswer != true) {
						System.out.println("Tip: If you solved one value, use subtraction to efficiently solve the other.");
						System.out.print("Enter HEART health kibble weight in grams: ");
						userHeartWeight = scnr.nextInt();
						heartFoodAnswer = isHeartFoodWeightValid(userHeartWeight, heartKibblePercent, gramsFood);
					}
				} catch (Exception e) {
					scnr.next();
				}
			}

			// display messages after correct answer
			System.out.println("Great job! You got it!");
			System.out.println("Let's move onto the next task.\n");

			// display second task: what distance to walk dog
			System.out.printf("Your second task is to find out the distance to walk %s.\n", dogName[chosenDogIndex]);
			System.out.printf("Here's the walking information for %s:\n", dogName[chosenDogIndex]);

			// choose display time to walk based on age
			chosenDogAge = dogAge[chosenDogIndex];
			if (chosenDogAge <= 7) {
				walkTime = 30;
			} else {
				walkTime = 20;
			}

			// choose display for dog pace based on size
			switch (chosenDogSize) {
			case "small":
				walkPace = 1.5;
				break;
			case "medium":
				walkPace = 2.3;
				break;
			case "large":
				walkPace = 3.1;
				break;
			}

			// display walk information
			System.out.printf("- %s can walk for %d minutes.\n", dogName[chosenDogIndex], walkTime);
			System.out.printf("- %s can walk at a pace of %.1f miles per hour (mph).\n", dogName[chosenDogIndex],
					walkPace);
			System.out.printf("How far will you need to walk %s?\n", dogName[chosenDogIndex]);

			// validate input is number
			// catch exceptions thrown for input values that are not numbers
			while (correctNumInput2 == false) {
				try {
					System.out.print("Enter WALK DISTANCE in miles (answer to the hundredth decimal place): ");
					userWalkDistance = scnr.nextDouble();
					correctNumInput2 = true;
				} catch (Exception e) { // if not number then display error message
					System.out.println("Oops! Try again and enter a valid number.");
					scnr.next(); // clears scanner object and prevents error message looping
				}
			}

			// call isWalkDistanceValid to validate walking distance answer
			walkDistanceAnswer = isWalkDistanceValid(userWalkDistance, walkTime, walkPace);

			// prompt for input again if an answer is incorrect
			// catch exceptions thrown for non-number values
			while (walkDistanceAnswer == false) {
				try {
					System.out.println("Hm, not quite. Try again! You got this!");
					System.out.println("Tip: Miles per hour means the distance in miles travelled in 1 hour.");
					System.out.println("Minutes are fractions of 1 hour. The distance can be scaled the same.");
					System.out.print("Enter WALK DISTANCE in miles (answer to the hundredth decimal place): ");
					userWalkDistance = scnr.nextDouble();
					walkDistanceAnswer = isWalkDistanceValid(userWalkDistance, walkTime, walkPace);
				} catch (Exception e) {
					scnr.next();
				}
			}

			// display messages after correct answer
			System.out.println("Great job! You got it!");
			System.out.println("Let's move onto the next task.\n");

			// display third task: does cost of additional services meet budget?
			// display additional services menu
			System.out.println("Here's a list of additional services you offer and their prices:");
			for (i = 0; i < addServices.length; i++) {
				System.out.printf("- %s: ", addServices[i]);
				System.out.printf("$%d\n", addServicesPrices[i]);
			}
			System.out.println();
			scnr.nextLine(); // scanner reads over the new line to process user input in third task

			// display and select additional services
			System.out.printf("It looks like %s is interested in the following additonal services:\n",
					dogName[chosenDogIndex]);

			firstServiceIndex = selectDog - 2;
			secondServiceIndex = selectDog;

			while (firstServiceIndex < 0) {
				firstServiceIndex = firstServiceIndex + 1;
			}

			while (secondServiceIndex > 3) {
				secondServiceIndex = secondServiceIndex - 1;
			}

			System.out.printf("- %s\n", addServices[firstServiceIndex]);
			System.out.printf("- %s\n", addServices[secondServiceIndex]);
			System.out.printf("They have a budget of $%d.\n", budget[chosenDogIndex]);
			System.out.println("Does the total cost of additional services fit their budget?");
			System.out.print("Enter YES or NO: ");
			userServicesYesNo = scnr.nextLine();

			firstServicePrice = addServicesPrices[firstServiceIndex];
			secondServicePrice = addServicesPrices[secondServiceIndex];
			dogBudget = budget[chosenDogIndex];

			// call isBudgetValid to validate if budget fits services cost
			budgetCompare = isBudgetValid(userServicesYesNo, firstServicePrice, secondServicePrice, dogBudget);

			// validate if answer is correct and message if correct
			while (budgetCompare == false) {
				System.out.println("Hm, not quite. Try again! You got this!");
				System.out.println("Tip: A budget is the maximum amount of money allowed to spend.");
				System.out.print("Enter YES or NO: ");
				userServicesYesNo = scnr.nextLine();
				budgetCompare = isBudgetValid(userServicesYesNo, firstServicePrice, secondServicePrice, dogBudget);
			}

			System.out.printf("Great job! You completed all the planner tasks for %s!\n\n", dogName[chosenDogIndex]);

			// display completed care plan
			System.out.printf("Here's the completed care plan message for %s:\n\n", dogName[chosenDogIndex]);
			System.out.println("------------------------------------------------------------------------------\n");
			System.out.printf("Hello %s!\n\n", dogName[chosenDogIndex]);
			System.out.printf("Thank you so much for choosing %s's Doggy Daycare.\n", username);
			System.out.println("We are excited to welcome you when you visit.");
			System.out.println("Here's what to expect during your stay with us:");
			System.out.println("- Premium meals adjusted to your diet recommendation:");
			System.out.printf("  %d grams digestive and %d grams heart heath kibble mix.\n", userDigestiveWeight,
					userHeartWeight);
			System.out.println("- Relaxing walks cutomized to provide the perfect amount of exercise:");
			System.out.printf("  a %.2f mile stroll around the stunning Java Park.\n", userWalkDistance);
			System.out.println("- A dazzling assortment of toys for endless engagement.");
			System.out.println("- Large and quiet resting area to recharge for more play!");

			userServicesYesNoCompare = userServicesYesNo.toUpperCase();
			if (userServicesYesNoCompare.equals("YES")) {
				System.out.printf("The additional services \"%s\" and \"%s\" will \n", addServices[firstServiceIndex],
						addServices[secondServiceIndex]);
				System.out.println("be provided at the budget discussed during initial consultation.");
			} else {
				System.out.println("Unfortunately, we are unable to provide the additonal ");
				System.out.println("services requested at the desired budget.");
				System.out.println("Please provide us an adjusted budget or request if desired.");
			}

			System.out.println("We look forward to hearing back from you! Don't hesitate let us know your ");
			System.out.println("thoughts for this plan. Thank you again for considering us for your care!\n");
			System.out.println("------------------------------------------------------------------------------\n");
			
			//prompt user play again or not
			//exit game when not yes is input
			System.out.println("Would you like to create a plan for another client?");
			System.out.print("Enter YES to play again or NO to exit game: ");
			playAgainYesNo = scnr.nextLine();
			
			if (!playAgainYesNo.toUpperCase().equals("YES")) {
				break;
			}
		}
		//exit message
		System.out.println("\nThank you for playing! Goodbye.\n");
		System.out.println("-- End game --");
	}

	public static boolean isDigestFoodWeightValid(int userDigestiveWeight, int digestiveKibblePercent, int gramsFood) {
		// declare BigDecimal class variables
		BigDecimal bigUserDigestiveWeight = new BigDecimal(userDigestiveWeight);
		BigDecimal bigDigestiveKibblePercent = new BigDecimal(digestiveKibblePercent);
		BigDecimal bigGramsFood = new BigDecimal(gramsFood);
		BigDecimal fraction = new BigDecimal("100");
		BigDecimal correctDigestiveWeight;

		//calculate digestive weight kibble amount
		correctDigestiveWeight = bigDigestiveKibblePercent.divide(fraction).multiply(bigGramsFood);

		if (bigUserDigestiveWeight.intValue() == correctDigestiveWeight.intValue()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isHeartFoodWeightValid(int userHeartWeight, int heartKibblePercent, int gramsFood) {
		// declare BigDecimal class variables
		BigDecimal bigUserHeartWeight = new BigDecimal(userHeartWeight);
		BigDecimal bigHeartKibblePercent = new BigDecimal(heartKibblePercent);
		BigDecimal bigGramsFood = new BigDecimal(gramsFood);
		BigDecimal fraction = new BigDecimal("100");
		BigDecimal correctHeartWeight;
		
		//calculate heart health kibble amount
		correctHeartWeight = bigHeartKibblePercent.divide(fraction).multiply(bigGramsFood);

		if (bigUserHeartWeight.intValue() == correctHeartWeight.intValue()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isWalkDistanceValid(double userWalkDistance, int walkTime, double walkPace) {
		// declare BigDecimal class variables
		BigDecimal bigUserWalkDistance = new BigDecimal(userWalkDistance);
		final double MIN_TO_HOURS = 60.0;
		double correctWalkDistance = 0;
		
		//calculate walk distance
		correctWalkDistance = (walkTime / MIN_TO_HOURS) * walkPace;
		BigDecimal bigCorrectWalkDistance = new BigDecimal (correctWalkDistance);
		bigCorrectWalkDistance = bigCorrectWalkDistance.setScale(2, RoundingMode.HALF_UP);
		bigUserWalkDistance = bigUserWalkDistance.setScale(2, RoundingMode.HALF_DOWN);

		if (bigUserWalkDistance.floatValue() == bigCorrectWalkDistance.floatValue()) {
			return true;
		} 
		else {
			return false;
		}
	}
	
	public static boolean isBudgetValid (String userServicesYesNo, int firstServicePrice, int secondServicePrice, int dogBudget) {
		int totalCost;
		String correctBudgetAnswer = "";
		String userServicesYesNoCaps;
		
		//convert to caps for uniform input
		userServicesYesNoCaps = userServicesYesNo.toUpperCase();
		
		//calculate total cost of services
		totalCost = firstServicePrice + secondServicePrice;
		
		//determines if cost exceed budget
		correctBudgetAnswer = "";
		if (dogBudget >= totalCost) {
			correctBudgetAnswer = "YES";
		}
		else {
			correctBudgetAnswer = "NO";
		}
	
		if (userServicesYesNoCaps.equals(correctBudgetAnswer)) {
			return true;
		}
		else {
			return false;
		}
	}
}