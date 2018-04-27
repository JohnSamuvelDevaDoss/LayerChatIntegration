package com.admin.app.model;

class Check {

	public static void main(String[] args) {
		
		for(int i=0;i<4;i++) {
			System.out.println("loop "+i);
			try {
				if(i/0 == 0) {
					System.out.println("worked");
				}
			}catch(Exception e) {
				System.out.println("exception catch");
				continue;
			}
		}

	}

}
