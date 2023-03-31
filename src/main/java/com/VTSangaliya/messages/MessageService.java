package com.VTSangaliya.messages;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.mashape.unirest.http.Unirest;

@Service
public class MessageService {

	public Boolean sendMessageToAnnouncement(String name, int amount, String mobile) {
		boolean flage = false;
		try {
			String sender_id = "TEJATC";
			int message = 137117;

			// int amount=51000;
			// String mobnumber = mobile;
			Unirest.post("https://www.fast2sms.com/dev/bulkV2")
					.header("authorization",
							"cDro7ugQejivbRVF8Bftk5G0IUYa3ZXOnM9qSpHT4L6sWCAwPEXZOfWV2joecvmphM8xINd3R6HYauPJ")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.body("sender_id=" + sender_id + "&message=" + message + "&variables_values=" + name + "|" + amount
							+ "&route=dlt&numbers=" + mobile)
					.asString();
			flage = true;
			System.out.println("announcement_send_message done");
			//Unirest.shutdown();
		} catch (Exception e) {
			e.getStackTrace();
		}
		return flage;
	}
//send message to aarthik sahyog
	public void sendMessageToAarthikSahyog(String name, String mobile, int amount, String receipt, LocalDate date) {

		try {
			String sender_id = "TEJATC";
			int message = 137278;


			// String mobnumber = mobile;
			Unirest.post("https://www.fast2sms.com/dev/bulkV2")
					.header("authorization",
							"cDro7ugQejivbRVF8Bftk5G0IUYa3ZXOnM9qSpHT4L6sWCAwPEXZOfWV2joecvmphM8xINd3R6HYauPJ")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.body("sender_id=" + sender_id + "&message=" + message + "&variables_values=" + name + "|"
							+ amount + "|" + receipt + "|" + date + "&route=dlt&numbers=" + mobile)
					.asString();
			System.out.println("receipt_send_message done");
			//Unirest.shutdown();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}


	//send message to samiti member for expenditures
		public void sendMessageToSamitiMemberForExpenditures(String fname,String sname,Integer amount,Integer boucherNo,String expendFor,String mobile) {

			try {
				String sender_id = "TEJATC";
				int message = 137646;


				// String mobnumber = mobile;
				Unirest.post("https://www.fast2sms.com/dev/bulkV2")
						.header("authorization",
								"cDro7ugQejivbRVF8Bftk5G0IUYa3ZXOnM9qSpHT4L6sWCAwPEXZOfWV2joecvmphM8xINd3R6HYauPJ")
						.header("Content-Type", "application/x-www-form-urlencoded")
						.body("sender_id=" + sender_id + "&message=" + message + "&variables_values=" + fname + "|" + sname + "|"
								+ amount + "|" + boucherNo + "|" + expendFor + "&route=dlt&numbers=" + mobile)
						.asString();
				System.out.println("expd_send_message done");
				//Unirest.shutdown();
			} catch (Exception e) {
				e.getStackTrace();
			}
		}

		//send message to pending announcement By admin
				public void sendMessageToPendingAnnoucementByAdmin(String name,Integer announceAmount,Integer pendingAmount,String mobile) {
//System.out.println("call send msg method");
					try {
						String sender_id = "TEJATC";
						int message = 138483;


						// String mobnumber = mobile;
						Unirest.post("https://www.fast2sms.com/dev/bulkV2")
								.header("authorization",
										"cDro7ugQejivbRVF8Bftk5G0IUYa3ZXOnM9qSpHT4L6sWCAwPEXZOfWV2joecvmphM8xINd3R6HYauPJ")
								.header("Content-Type", "application/x-www-form-urlencoded")
								.body("sender_id=" + sender_id + "&message=" + message + "&variables_values=" + name + "|"
										+ announceAmount + "|" + pendingAmount + "|" + "&route=dlt&numbers=" + mobile)
								.asString();
						System.out.println("pending_announce_send_message done");

					} catch (Exception e) {
						e.getStackTrace();
					}
				}

}
