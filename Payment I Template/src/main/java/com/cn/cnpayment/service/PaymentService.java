package com.cn.cnpayment.service;

import com.cn.cnpayment.dal.PaymentDAL;
import com.cn.cnpayment.exception.ElementAlreadyExistException;
import com.cn.cnpayment.exception.InvalidInputException;
import com.cn.cnpayment.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cn.cnpayment.entity.Payment;

import java.util.ArrayList;
import java.util.List;

/**
Complete the PaymentService class as mentioned below:
Tasks:-
a. Autowire PaymentDAL.
b. Complete the following methods:
1. getPaymentById(int id): This method fetches payment from the dal layer for a specific id.
2. getPaymentByPaymentType(String paymentType): This method fetches a list of Payment from the dal layer based on the paymentType received.
3. getPaymentByDescriptionKeyword(String keyword): This method fetches a list of payments from the dal layer based on the keyword received.
4. getAllPayments(): This method fetches a list of payments from the dal layer.
5. addPayment(Payment payment): This method saves payment entity into the database using the dal layer.
**/

@Service
public class PaymentService {

	// Auto-wire necessary DAl layer object;
	@Autowired
	PaymentDAL paymentDAL;

	@Transactional
	public Payment getPaymentById(int id) {
		Payment payment = paymentDAL.getById(id);
		if (payment == null) throw new NotFoundException("Payment for id not exist");
		return payment;
	}

	@Transactional
	public List<Payment> getPaymentByPaymentType(String paymentType) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Cash");
		list.add("Debit");
		list.add("Credit");

		Boolean bool = false;
		for (String a: list) {
			if (a.equalsIgnoreCase(paymentType)) {
				bool = true;
				break;
			}
		}
		if (!bool) throw new InvalidInputException("Invalid input ");

		List<Payment> payments = paymentDAL.getByPaymentType(paymentType);
		// Todo:
		if (payments.isEmpty()) throw new NotFoundException("No Payment found");
		return payments;
	}

	@Transactional
	public List<Payment> getPaymentByDescriptionKeyword(String keyword) {
		List<Payment> payments = paymentDAL.getByPaymentDescription(keyword);
		if (payments.isEmpty()) throw new NotFoundException("No Payment found");

		return payments;
	}

	@Transactional
	public List<Payment> getAllPayments() {
		List<Payment> payments = paymentDAL.getAllPayments();
		if (payments.isEmpty()) throw new NotFoundException("No Payment found");
		return payments;
	}

	@Transactional
	public void addPayment(Payment payment)  {
		//Todo:
//		payment.getId()

		if (paymentDAL.getById(payment.getId()) != null) throw new ElementAlreadyExistException("Payment id already exist");
		paymentDAL.addPayment(payment);
	}

}

