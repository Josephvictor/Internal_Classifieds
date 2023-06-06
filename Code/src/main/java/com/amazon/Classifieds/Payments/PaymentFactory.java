package com.amazon.Classifieds.Payments;

interface googlePay {
	
	boolean isBankInterfaceUp = true;
	
	default boolean payGoogle(int amount) {
		
		System.out.println("[GooglePay] Processing payment of amount :\u20b9"+amount);
		
		if(isBankInterfaceUp) {
			
			System.out.println("[GooglePay] Payment Process is successfully completed");
			onSuccess("GooglePay received the amount \u20b9"+amount);
			return true;
		}
		return false;
	}
	
	void onSuccess(String message);
	void onFailure(int errorCode);
	
}

interface PayTM{
	
	boolean isBankInterfaceUp = true;
	
	default boolean payPayTM(int amount) {
		
		System.out.println("[PayTM] Processing payment of amount :\u20b9"+amount);
		
		if(isBankInterfaceUp) {
			
			System.out.println("[PayTM] Payment Process is successfully completed");
			onSuccess("PayTm successfully received the amount \u20b9"+amount);
			return true;
		}
		return false;
	}
	
	void onSuccess(String message);
	void onFailure(int errorCode);
}

interface AmazonPay{
	
	boolean isBankInterfaceUp = true;
	
	default boolean payAmazon(int amount) {
		
		System.out.println("[AmazonPay] Payment Process initiated for amount \u20b9"+amount);
		
		if(isBankInterfaceUp) {
			System.out.println("[AmazonPay] Payment Process Success");
			onSuccess("AmazonPay received the payment of amount \u20b9"+amount);
			return true;
		}
		return false;
	}
	
	void onSuccess(String message);
	void onFailure(int errorCode);
}

interface Payment extends googlePay,PayTM,AmazonPay{
	
}

class ClassifiedsPayService implements Payment{

	@Override
	public void onSuccess(String message) {
		System.out.println("[ClassifiedsPayService] "+message);
		System.out.println("[ClassifiedsPayService] Payment Received. Proceeding next");
		
	}

	@Override
	public void onFailure(int errorCode) {
		
		System.out.println("[ClassifiedsPayService] Payment failed with error code: "+errorCode);
	}
	
}

public class PaymentFactory {
	
	public static boolean paymentService(int option,int amount) {
		
		Payment method =  null;
		
		if(option == 1) {
			method = new ClassifiedsPayService();
			return method.payGoogle(amount);
		}else if(option == 2) {
			method = new ClassifiedsPayService();
			return method.payPayTM(amount);
		}else if(option == 3) {
			method = new ClassifiedsPayService();
			return method.payAmazon(amount);
		}else {
			System.err.println("Invalid option.. Try Again");
			return false;
		}
	}
}
