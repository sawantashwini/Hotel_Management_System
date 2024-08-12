<%@page import="com.dto.PaymentDto"%>
<%@page import="com.dto.OrderBillDto"%>
<%@page import="com.service.OrderService"%>
<%
    OrderBillDto dto = new OrderBillDto();

	dto.setId(Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id")));
	dto.setBank_id_fk(Integer.parseInt(request.getParameter("bank_id_fk") == null ? "0" : request.getParameter("bank_id_fk")));
	dto.setStatus(request.getParameter("status") == null ? "Active" : request.getParameter("status"));
	dto.setTable_id(Integer.parseInt(request.getParameter("table_id") == null ? "0" : request.getParameter("table_id")));
	dto.setWithout_gst_amount(Float.parseFloat(request.getParameter("without_gst_amount") == null || request.getParameter("without_gst_amount") == "" ? "0.00" : request.getParameter("without_gst_amount")));
    dto.setBill_date(request.getParameter("bill_date") == null ? "" : request.getParameter("bill_date"));
    dto.setSession_year(request.getParameter("session_year") == null ? "" : request.getParameter("session_year"));
    dto.setUser_id_fk(Integer.parseInt(request.getParameter("user_id_fk") == null ? "0" : request.getParameter("user_id_fk")));
    dto.setManager_name(request.getParameter("manager_name") == null ? "" : request.getParameter("manager_name"));
    
    
    dto.setWithout_gst_amount(Float.parseFloat(request.getParameter("without_gst_amount") == null || request.getParameter("without_gst_amount") == "" ? "0.00" : request.getParameter("without_gst_amount")));
    dto.setGst_amount(Float.parseFloat(request.getParameter("gst_amount") == null || request.getParameter("gst_amount") == "" ? "0.00" : request.getParameter("gst_amount")));
    dto.setWith_gst_amount(Float.parseFloat(request.getParameter("with_gst_amount") == null || request.getParameter("with_gst_amount") == "" ? "0.00" : request.getParameter("with_gst_amount")));
    dto.setOld_due_amount(Float.parseFloat(request.getParameter("old_due_amount") == null || request.getParameter("old_due_amount") == "" ? "0.00" : request.getParameter("old_due_amount")));
    dto.setFinal_amount(Float.parseFloat(request.getParameter("final_amount") == null || request.getParameter("final_amount") == "" ? "0.00" : request.getParameter("final_amount")));
    dto.setPaid_amount(Float.parseFloat(request.getParameter("paid_amount") == null || request.getParameter("paid_amount") == "" ? "0.00" : request.getParameter("paid_amount")));
    dto.setDiscount_amount(Float.parseFloat(request.getParameter("discount_amount") == null || request.getParameter("discount_amount") == "" ? "0.00" : request.getParameter("discount_amount")));
    dto.setPayment_mode(request.getParameter("payment_mode") == null ? "" : request.getParameter("payment_mode"));
    dto.setCash_amount(Float.parseFloat(request.getParameter("cash_amount") == null || request.getParameter("cash_amount") == "" ? "0.00" : request.getParameter("cash_amount")));
    dto.setOnline_amount(Float.parseFloat(request.getParameter("online_amount") == null || request.getParameter("online_amount") == "" ? "0.00" : request.getParameter("online_amount")));
    dto.setOnline_date(request.getParameter("online_date") == null ? "" : request.getParameter("online_date"));
    dto.setOnline_remark(request.getParameter("online_remark") == null ? "" : request.getParameter("online_remark"));
    dto.setOnline_way(request.getParameter("online_way") == null ? "" : request.getParameter("online_way"));
	dto.setRemark(request.getParameter("remark") == null ? "" : request.getParameter("remark"));
    dto.setGst_status(request.getParameter("gst_status") == null ? "" : request.getParameter("gst_status"));
  
 		// ************PAYMENT DTO VALUES***********
 		PaymentDto pay_dto = new PaymentDto();

 		// Integer
 		pay_dto.setBill_id_fk(Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id")));
 		pay_dto.setBank_id_fk(Integer
 				.parseInt(request.getParameter("bank_id_fk") == null ? "0" : request.getParameter("bank_id_fk")));
 		// Float
 		pay_dto.setOnline_amount(Float.parseFloat(
 				request.getParameter("online_amount") == null || request.getParameter("online_amount") == "" ? "0.0" : request.getParameter("online_amount")));
 		pay_dto.setCash_amount(Float
 				.parseFloat(request.getParameter("cash_amount") == null || request.getParameter("cash_amount") == "" ? "0.0" : request.getParameter("cash_amount")));
 		// String
 		pay_dto.setRemark(request.getParameter("remark") == null ? "0" : request.getParameter("remark"));
 		pay_dto.setOnline_remark(
 				request.getParameter("online_remark") == null ? "0" : request.getParameter("online_remark"));
 		pay_dto.setOnline_date(request.getParameter("online_date") == null ? "" : request.getParameter("online_date"));
 		pay_dto.setOnline_way(request.getParameter("online_way") == null ? "" : request.getParameter("online_way"));
 		pay_dto.setIn_date(request.getParameter("bill_date") == null ? "" : request.getParameter("bill_date"));
 		pay_dto.setStatus("Active");
 		pay_dto.setType("Menu Table Bill");
		
 		//customer Details
 		dto.setCust_id_fk(Integer.parseInt(request.getParameter("cust_id_fk") == null ? "0" : request.getParameter("cust_id_fk")));
 	    dto.setCust_name(request.getParameter("cust_name") == null ? "" : request.getParameter("cust_name"));
 	    dto.setCust_mob_no(request.getParameter("cust_mob_no") == null ? "" : request.getParameter("cust_mob_no"));
   dto.setCust_address(request.getParameter("cust_address") == null ? "" : request.getParameter("cust_address"));
   dto.setCompany_name(request.getParameter("cust_company_name") == null ? "" : request.getParameter("cust_company_name"));
   dto.setGst_no(request.getParameter("cust_gst_no") == null ? "" : request.getParameter("cust_gst_no"));
   dto.setDob(request.getParameter("cust_dob") == null ? "" : request.getParameter("cust_dob"));
   dto.setDoa(request.getParameter("cust_doa") == null ? "" : request.getParameter("cust_doa"));
 	    dto.setRegular(request.getParameter("regular") == null ? "" : request.getParameter("regular"));
 		
	    // customer type logic
	    if(dto.getCust_id_fk()==0){dto.setCust_type("New");}
	    else{dto.setCust_type("Exist");}
	    
    
    
	    // return amount logic
	    float return_amount = (dto.getCash_amount() + dto.getOnline_amount()) - dto.getFinal_amount();
		if(return_amount>0) {
		
		dto.setReturn_amount(return_amount);
		
		if(dto.getPayment_mode().equalsIgnoreCase("cash")) {
			pay_dto.setCash_amount(pay_dto.getCash_amount()-return_amount);
			dto.setCash_amount(dto.getCash_amount()-return_amount);
		}
		
		else if(dto.getPayment_mode().equalsIgnoreCase("online")) {
			pay_dto.setCash_amount(pay_dto.getOnline_amount()-return_amount);
			dto.setCash_amount(dto.getOnline_amount()-return_amount);
		}
		
		else {
			pay_dto.setCash_amount(pay_dto.getCash_amount()-(return_amount/2));
			dto.setCash_amount(dto.getCash_amount()-(return_amount/2));
			
			pay_dto.setOnline_amount(pay_dto.getOnline_amount()-(return_amount/2));
			dto.setOnline_amount(dto.getOnline_amount()-(return_amount/2));
			}
			
		}
		
	String flag = request.getParameter("flag") == null ? "" : request.getParameter("flag");
	OrderService ser = new OrderService();
	boolean b = false;
	int id=0;
	
	
	
    
   

    if (dto.getId() == 0) {
        id = ser.insertOrderWithoutBilling(dto, request, config);
        dto.setId(id);
        if(id>0){
        	b=true;
        }
    } 
    
	if(dto.getId() != 0 && flag.equalsIgnoreCase("direct bill")) {
       id = ser.completeOrderBilling(dto,pay_dto, request, config);
       if(id>0){
       	b=true;
       }
    }
	
	if(dto.getId() != 0 && flag.equalsIgnoreCase("update bill")) {
       id = ser.updateOrderBilling(dto,pay_dto, request, config);
       if(id>0){
       	b=true;
       }
    }
		
%>
<input type="hidden" id="item_result" value="<%=b%>">
<input type="hidden" id="order_id_val" value="<%=id%>">




