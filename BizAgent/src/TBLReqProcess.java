import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import com.mysql.jdbc.Driver;

public class TBLReqProcess implements Runnable {
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	private final String DB_URL = "jdbc:mysql://222.122.203.68/dhn?characterEncoding=utf8";
	private final String USER_NAME = "root";
	private final String PASSWORD = "sjk4556!!22";
	
	private final String NURL = "jdbc:mysql://125.128.249.42/bizsms";
	private final String NUSER_NAME = "bizsms";
	private final String NPASSWORD = "!@nanum0915";
	
	
	public static boolean isRunning = false;
	public Logger log;
	
	public void run() {
		if(!TBLReqProcess.isRunning) {
			Proc();
		} 
	}
	
	private synchronized  void Proc() {
		TBLReqProcess.isRunning = true;	
		
		//log.info("TBL RESULT PROC 실행");
		Connection conn = null;
		Connection nconn = null;
		Statement tbl_result = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);

			tbl_result = conn.createStatement();
						
	        Date reserve_dt = new Date();
	        SimpleDateFormat rd = new SimpleDateFormat("yyyyMMddHHmmss");
	 		
			String sqlstr = "select a.*" + 
					"              ,b.mem_userid " + 
					"              ,b.mem_id " + 
					"              ,b.mem_level" + 
					"              ,b.mem_phn_agent" + 
					"              ,b.mem_sms_agent" + 
					"              ,b.mem_2nd_send" + 
					"          from TBL_REQUEST_RESULT a inner join cb_member b on SPLIT(a.MSGID, '_', 1)=b.mem_id" + 
					"         where ( a.reserve_dt < '" + rd.format(reserve_dt) + "'" + 
					"              or a.reserve_dt = '00000000000000')";
			ResultSet rs = tbl_result.executeQuery(sqlstr);
			
			String msgtype = "LMS";
			String pre_mem_id = "";
			Price_info price = null;
			
			while(rs.next()) {
				String userid = rs.getString("mem_userid");
				if(rs.getString("SMS_KIND").equals("S")) {
					msgtype = "SMS";
				} else {
					msgtype = "LMS";
				}
				log.info("MSG ID : " + rs.getString("MSGID") + " 진행 시작 !!");
				String msg_id = rs.getString("MSGID");
				String mem_id = rs.getString("mem_id");
				String mem_lv = rs.getString("mem_level");
				String mem_phn = rs.getString("mem_phn_agent");
				String mem_sms = rs.getString("mem_sms_agent");
				String mem_p_invoice = rs.getString("P_INVOICE");
				String mem_resend = rs.getString("mem_2nd_send");
				String msg_sms = rs.getString("MSG_SMS");
				String sent_key = rs.getString("REMARK4");
				String phn = "";
				String mem_2nd_type = "";
				try {
					if(rs.getString("PHN").substring(0, 1).equals("82")) {
						phn = "0" + rs.getString("PHN").substring(2);
					} else {
						phn = rs.getString("PHN");
					}
				} catch (StringIndexOutOfBoundsException e) {
					
				}
				
				if(mem_p_invoice != null && !mem_p_invoice.isEmpty() && rs.getString("MESSAGE_TYPE").equals("ph")) {
					mem_resend = mem_p_invoice;
					switch(mem_resend) {
					case "GREEN_SHOT":
						mem_2nd_type = "gs";
						break;
					case "NASELF":
						mem_2nd_type = "ns";
						break;
					}
				} else {
					mem_2nd_type = rs.getString("MESSAGE_TYPE");
				}
				
				// 사용자별 단가를 불러 옴.
				if(pre_mem_id != mem_id) {
					price = new Price_info(Integer.valueOf(mem_id));
					pre_mem_id = mem_id;
				}
				
				String insstr = "insert into cb_msg_"+userid+"(MSGID," + 
									        "AD_FLAG," + 
									        "BUTTON1," + 
									        "BUTTON2," + 
									        "BUTTON3," + 
									        "BUTTON4," + 
									        "BUTTON5," + 
									        "CODE," + 
									        "IMAGE_LINK," + 
									        "IMAGE_URL," + 
									        "KIND," + 
									        "MESSAGE," + 
									        "MESSAGE_TYPE," + 
									        "MSG," + 
									        "MSG_SMS," + 
									        "ONLY_SMS," + 
									        "P_COM," + 
									        "P_INVOICE," + 
									        "PHN," + 
									        "PROFILE," + 
									        "REG_DT," + 
									        "REMARK1," + 
									        "REMARK2," + 
									        "REMARK3," + 
									        "REMARK4," + 
									        "REMARK5," + 
									        "RES_DT," + 
									        "RESERVE_DT," + 
									        "RESULT," + 
									        "S_CODE," + 
									        "SMS_KIND," + 
									        "SMS_LMS_TIT," + 
									        "SMS_SENDER," + 
									        "SYNC," + 
									        "TMPL_ID," + 
									        "mem_userid)" + 
								"	  values(?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?," + 
									        "?)";
				
				PreparedStatement insSt = conn.prepareStatement(insstr);
				insSt.setString(1 , rs.getString("MSGID"));
				insSt.setString(2 , rs.getString("AD_FLAG"));
				insSt.setString(3 , rs.getString("BUTTON1"));
				insSt.setString(4 , rs.getString("BUTTON2"));
				insSt.setString(5 , rs.getString("BUTTON3"));
				insSt.setString(6 , rs.getString("BUTTON4"));
				insSt.setString(7 , rs.getString("BUTTON5"));
				insSt.setString(8 , rs.getString("CODE"));
				insSt.setString(9 , rs.getString("IMAGE_LINK"));
				insSt.setString(10, rs.getString("IMAGE_URL"));
				insSt.setString(11, rs.getString("KIND"));
				insSt.setString(12, rs.getString("MESSAGE"));
				insSt.setString(13, mem_2nd_type);
				insSt.setString(14, rs.getString("MSG"));
				insSt.setString(15, rs.getString("MSG_SMS"));
				insSt.setString(16, rs.getString("ONLY_SMS"));
				insSt.setString(17, rs.getString("P_COM"));
				insSt.setString(18, rs.getString("P_INVOICE"));
				insSt.setString(19, rs.getString("PHN"));
				insSt.setString(20, rs.getString("PROFILE"));
				insSt.setString(21, rs.getString("REG_DT"));
				insSt.setString(22, rs.getString("REMARK1"));
				insSt.setString(23, rs.getString("REMARK2"));
				insSt.setString(24, rs.getString("REMARK3"));
				insSt.setString(25, rs.getString("REMARK4"));
				insSt.setString(26, rs.getString("REMARK5"));
				insSt.setString(27, rs.getString("RES_DT"));
				insSt.setString(28, rs.getString("RESERVE_DT"));
				insSt.setString(29, rs.getString("RESULT"));
				insSt.setString(30, rs.getString("S_CODE"));
				insSt.setString(31, rs.getString("SMS_KIND"));
				insSt.setString(32, rs.getString("SMS_LMS_TIT"));
				insSt.setString(33, rs.getString("SMS_SENDER"));
				insSt.setString(34, rs.getString("SYNC"));
				insSt.setString(35, rs.getString("TMPL_ID"));
				insSt.setString(36, rs.getString("mem_userid"));
				
				//log.info("한글 : " +rs.getString("MSG")+ "Insert Qeury : " + insSt.toString());
				
				insSt.executeUpdate();
				insSt.close();
				
				if(rs.getString("RESULT").equals("Y")) {
				// 발신 성공이면 금액 차감
					String kind = "";
					float amount = 0;
					String memo = "";
					float payback = 0;
					float admin_amt = 0;
					Statement upd = conn.createStatement();
					String upstr = "update cb_wt_msg_sent " ;
					if(rs.getString("MESSAGE_TYPE").equals("ft")) {
						if(rs.getString("IMAGE_URL").isEmpty()) {
							upstr = upstr + "set mst_ft = ifnull(mst_ft, 0) + 1 ";
							kind = "F";
							amount = price.member_price.price_ft;
							payback = price.member_price.price_ft - price.parent_price.price_ft;
							admin_amt = price.base_price.price_ft;
							memo = "친구톡(텍스트)";
						} else {
							upstr = upstr + "set mst_ft_img = ifnull(mst_ft_img, 0) + 1 ";
							kind = "I";
							amount = price.member_price.price_ft_img;
							payback = price.member_price.price_ft_img - price.parent_price.price_ft_img;
							admin_amt = price.base_price.price_ft_img;
							memo = "친구톡(이미지)";
						}
						upstr = upstr + " where mst_id = " + sent_key;
						
						// 친구톡 성공 목록 저장용
						// 기존에 있으면 지우고 추가
						String fldelstr = "delete from cb_friend_list where mem_id = ? and phn = ? ";
						PreparedStatement fldel = conn.prepareStatement(fldelstr);
						fldel.setInt(1, Integer.valueOf(mem_id));
						fldel.setString(2, phn);
						fldel.executeUpdate();
						fldel.close();
						
						String flstr = "insert into cb_friend_list(mem_id, phn, last_send_date) values(?,?,?)";
						PreparedStatement flins = conn.prepareStatement(flstr);
						flins.setInt(1, Integer.valueOf(mem_id));
						flins.setString(2, phn);
						flins.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
						
						flins.executeUpdate();
						flins.close();
						
					}else if(rs.getString("MESSAGE_TYPE").equals("at")) {
						upstr = upstr + "set mst_at = ifnull(mst_at, 0) + 1 where mst_id = " + sent_key;
						kind = "A";
						amount = price.member_price.price_at;
						payback = price.member_price.price_at - price.parent_price.price_at;
						admin_amt = price.base_price.price_at;
						memo = "알림톡";
					}
					
					upd.execute(upstr);
					upd.close();
					
					String amtStr = "insert into cb_amt_" + userid + "(amt_datetime," +
					                                                  "amt_kind," +
					                                                  "amt_amount," +
					                                                  "amt_memo," +
					                                                  "amt_reason," +
					                                                  "amt_payback," +
					                                                  "amt_admin)" +
					                                           "values(?," +
					                      					          "?," +	
					                      					          "?," +	
					                      					          "?," +	
					                      					          "?," +	
					                      					          "?," +	
					                      					          "?)";
					PreparedStatement amtins = conn.prepareStatement(amtStr);
					amtins.setTimestamp(1, new java.sql.Timestamp(System.currentTimeMillis())); 
					amtins.setString(2, kind); 
					amtins.setFloat(3, amount); 
					amtins.setString(4, memo); 
					amtins.setString(5, msg_id); 
					amtins.setFloat(6, payback); 
					amtins.setFloat(7, admin_amt); 
					
					amtins.executeUpdate();
					amtins.close();

				} else {
				// 실패 이면서 2차 발신 대상이면 2차 발신 Table 에 Insert	
					if(rs.getString("MESSAGE_TYPE").equals("ft")) {
	 					// 친구톡 발송 실패시 친구톡 성공 목록에서 삭제
						String fldelstr = "delete from cb_friend_list where mem_id = ? and phn = ? ";
						PreparedStatement fldel = conn.prepareStatement(fldelstr);
						fldel.setInt(1, Integer.valueOf(mem_id));
						fldel.setString(2, phn);
						fldel.executeUpdate();
						fldel.close();
					}

					String wtudstr;
					String msgudstr;
					PreparedStatement wtud;
					PreparedStatement msgud; 
					
					if( !rs.getString("MESSAGE").equals("InvalidPhoneNumber") && !mem_resend.isEmpty() && !msg_sms.isEmpty() && !rs.getString("SMS_SENDER").isEmpty() ) {
						
						String 	amtStr = "insert into cb_amt_" + userid + "(amt_datetime," +
										                                   "amt_kind," +
										                                   "amt_amount," +
										                                   "amt_memo," +
										                                   "amt_reason," +
										                                   "amt_payback," +
										                                   "amt_admin)" +
										                            "values(?," +
										    					           "?," +	
										    					           "?," +	
										    					           "?," +	
										    					           "?," +	
										    					           "?," +	
										    					           "?)";
						PreparedStatement amtins;
						String kind = "";
						float amount = 0;
						String memo = "";
						float payback = 0;
						float admin_amt = 0;
						
						String nanoit;
						PreparedStatement nanoins;
						
						switch(mem_resend) {
						case "015":
							nanoit = "insert into cb_nanoit_msg(msg_type, remark4, phn) values(?, ?, ?)";
							nanoins = conn.prepareStatement(nanoit);
							nanoins.setString(1, "015");
							nanoins.setString(2, sent_key);
							nanoins.setString(3, phn);
							nanoins.executeUpdate();
							nanoins.close();
							break;
						case "PHONE":
							nanoit = "insert into cb_nanoit_msg(msg_type, remark4, phn) values(?, ?, ?)";
							nanoins = conn.prepareStatement(nanoit);
							nanoins.setString(1, "PHONE");
							nanoins.setString(2, sent_key);
							nanoins.setString(3, phn);
							nanoins.executeUpdate();
							nanoins.close();
							break;			
						case "BKG":
							String bkgstr ="insert into cb_mms_msg(SUBJECT"
									                          + ", PHONE"
									                          + ", CALLBACK"
									                          + ", STATUS"
									                          + ", MSG"
									                          + ", BILL_ID"
									                          + ", TYPE"
									                          + ", ETC1"
									                          + ", ETC2"
									                          + ", ETC4"
									                          + ", REQDATE) "
									                    + "values( ?"
									                          + ", ?"
									                          + ", ?"
									                          + ", ? "
									                          + ", ?"
									                          + ", ?"
									                          + ", ?"
									                          + ", ?"
									                          + ", ?"
									                          + ", ?"
									                          + ", ?)";
							PreparedStatement bkgins = conn.prepareStatement(bkgstr);
							bkgins.setString(1,  rs.getString("SMS_LMS_TIT").replaceAll("\\r\\n|\\r|\\n", ""));
							bkgins.setString(2, phn);
							bkgins.setString(3, rs.getString("SMS_SENDER"));
							bkgins.setString(4, "0");
							bkgins.setString(5, msg_sms);
							bkgins.setString(6, rs.getString("SMS_SENDER"));
							bkgins.setString(7, "0");
							bkgins.setString(9, msg_id);
							bkgins.setString(10, sent_key);
							bkgins.setString(12, mem_id);
							if(rs.getString("RESERVE_DT").equals("00000000000000")) {
								bkgins.setString(13, rd.format(reserve_dt));
							}else {
								bkgins.setString(13, rs.getString("RESERVE_DT"));
							}
							bkgins.executeUpdate();
							bkgins.close();
							
							wtudstr = "update cb_wt_msg_sent set mst_grs=ifnull(mst_grs,0)+1 where mst_id=?";
							wtud = conn.prepareStatement(wtudstr);
							wtud.setString(1, sent_key);
							wtud.executeUpdate();
							wtud.close();
							
							msgudstr = "update cb_msg_" + userid + " set MESSAGE_TYPE='gs',CODE='GRS', MESSAGE = '결과 수신대기' where MSGID=?";
							msgud = conn.prepareStatement(msgudstr);
							msgud.setString(1, msg_id);
							msgud.executeUpdate();
							msgud.close();
												
							kind = "P";
							amount = price.member_price.price_grs;
							payback = price.member_price.price_grs - price.parent_price.price_grs;
							admin_amt = price.base_price.price_grs;
							memo = "웹(A)";
							if(amount == 0 || amount == 0.0f) {
								amount = admin_amt;
							}

							amtins = conn.prepareStatement(amtStr);
							amtins.setTimestamp(1, new java.sql.Timestamp(System.currentTimeMillis())); 
							amtins.setString(2, kind); 
							amtins.setFloat(3, amount); 
							amtins.setString(4, memo); 
							amtins.setString(5, msg_id); 
							amtins.setFloat(6, payback); 
							amtins.setFloat(7, admin_amt); 
							
							amtins.executeUpdate();
							amtins.close();
							
							break;
						case "GREEN_SHOT":
							if(msgtype.equals("SMS")) {
								String funsmsstr = "insert into cb_sms_msg(TR_PHONE"
										                               + ",TR_CALLBACK"
										                               + ",TR_ORG_CALLBACK"
										                               + ",TR_SENDSTAT"
										                               + ",TR_MSG"
										                               + ",TR_MSGTYPE"
										                               + ",TR_ETC1"
										                               + ",TR_ETC2"
										                               + ",TR_ETC4"
										                               + ",TR_SENDDATE)"
										                               + "values("
										                               + " ?"
										                               + ",?"
										                               + ",?"
										                               + ",?"
										                               + ",?"
										                               + ",?"
										                               + ",?"
										                               + ",?"
										                               + ",?"
										                               + ",?)";
								PreparedStatement funsmsins = conn.prepareStatement(funsmsstr);
								funsmsins.setString(1, phn);
								funsmsins.setString(2, rs.getString("SMS_SENDER"));
								funsmsins.setString(3, rs.getString("SMS_SENDER"));
								funsmsins.setString(4, "0");
								funsmsins.setString(5, msg_sms);
								funsmsins.setString(6, "0");
								funsmsins.setString(7, msg_id);
								funsmsins.setString(8, sent_key);
								funsmsins.setString(9, mem_id);
								if(rs.getString("RESERVE_DT").equals("00000000000000")) {
									funsmsins.setString(10, rd.format(reserve_dt));
								}else {
									funsmsins.setString(10, rs.getString("RESERVE_DT"));
								} 
								funsmsins.executeUpdate();
								funsmsins.close();
								
								wtudstr = "update cb_wt_msg_sent set mst_grs_sms=ifnull(mst_grs_sms,0)+1 where mst_id=?";
								wtud = conn.prepareStatement(wtudstr);
								wtud.setString(1, sent_key);
								wtud.executeUpdate();
								wtud.close();
								
								msgudstr = "update cb_msg_" + userid + " set MESSAGE_TYPE='gs',CODE='GRS', MESSAGE = '결과 수신대기', SMS_KIND='S' where MSGID=?";
								msgud = conn.prepareStatement(msgudstr);
								msgud.setString(1, msg_id);
								msgud.executeUpdate();
								msgud.close();
																				
								kind = "P";
								amount = price.member_price.price_grs_sms;
								payback = price.member_price.price_grs_sms - price.parent_price.price_grs_sms;
								admin_amt = price.base_price.price_grs_sms;
								memo = "웹(A) SMS";
								if(amount == 0 || amount == 0.0f) {
									amount = admin_amt;
								}

								amtins = conn.prepareStatement(amtStr);
								amtins.setTimestamp(1, new java.sql.Timestamp(System.currentTimeMillis())); 
								amtins.setString(2, kind); 
								amtins.setFloat(3, amount); 
								amtins.setString(4, memo); 
								amtins.setString(5, msg_id); 
								amtins.setFloat(6, payback); 
								amtins.setFloat(7, admin_amt); 
								
								amtins.executeUpdate();
								amtins.close();
								
							}else if(msgtype.equals("LMS")) {
								nanoit = "insert into cb_nanoit_msg(msg_type, remark4, phn) values(?, ?, ?)";
								nanoins = conn.prepareStatement(nanoit);
								nanoins.setString(1, "GRS");
								nanoins.setString(2, sent_key);
								nanoins.setString(3, phn);
								nanoins.executeUpdate();
								nanoins.close();
								break;		
							}
								
							break;
						case "NASELF":
							if(nconn == null) {
								nconn = DriverManager.getConnection(NURL, NUSER_NAME, NPASSWORD);
							}
							
							// 나셀프 수신거부 List 조회 후 수신거부 처리
							Statement nblock = nconn.createStatement();
							String nblockstr = "select count(1) as cnt from sdk_block_hp where hp = '" + phn + "'";
							ResultSet nrs = nblock.executeQuery(nblockstr);
							
							if(nrs.getInt("cnt") > 0) {
								wtudstr = "update cb_wt_msg_sent set mst_err_nas=ifnull(mst_err_nas,0)+1 where mst_id=?";
								wtud = conn.prepareStatement(wtudstr);
								wtud.setString(1, sent_key);
								wtud.executeUpdate();
								wtud.close();
								
								msgudstr = "update cb_msg_" + userid + " set MESSAGE_TYPE='ns',CODE='NAS', MESSAGE = '수신거부', RESULT = 'N' where MSGID=?";
								msgud = conn.prepareStatement(msgudstr);
								msgud.setString(1, msg_id);
								msgud.executeUpdate();
								msgud.close();
							} else {
								if(msgtype.equals("SMS")) {
									String nassmsstr = "insert into cb_nas_sms_msg(TR_PHONE"
											                               + ",TR_CALLBACK"
											                               + ",TR_ORG_CALLBACK"
											                               + ",TR_SENDSTAT"
											                               + ",TR_MSG"
											                               + ",TR_MSGTYPE"
											                               + ",TR_ETC1"
											                               + ",TR_ETC2"
											                               + ",TR_ETC4"
											                               + ",TR_SENDDATE)"
											                               + "values("
											                               + " ?"
											                               + ",?"
											                               + ",?"
											                               + ",?"
											                               + ",?"
											                               + ",?"
											                               + ",?"
											                               + ",?"
											                               + ",?"
											                               + ",?)";
									PreparedStatement nassmsins = conn.prepareStatement(nassmsstr);
									nassmsins.setString(1, phn);
									nassmsins.setString(2, rs.getString("SMS_SENDER"));
									nassmsins.setString(3, rs.getString("SMS_SENDER"));
									nassmsins.setString(4, "0");
									nassmsins.setString(5, msg_sms);
									nassmsins.setString(6, "0");
									nassmsins.setString(7, msg_id);
									nassmsins.setString(8, sent_key);
									nassmsins.setString(9, mem_id);
									if(rs.getString("RESERVE_DT").equals("00000000000000")) {
										nassmsins.setString(10, rd.format(reserve_dt));
									}else {
										nassmsins.setString(10, rs.getString("RESERVE_DT"));
									} 
									nassmsins.executeUpdate();
									nassmsins.close();
									
									wtudstr = "update cb_wt_msg_sent set mst_nas_sms=ifnull(mst_nas_sms,0)+1 where mst_id=?";
									wtud = conn.prepareStatement(wtudstr);
									wtud.setString(1, sent_key);
									wtud.executeUpdate();
									wtud.close();
									
									msgudstr = "update cb_msg_" + userid + " set MESSAGE_TYPE='ns',CODE='NAS', MESSAGE = '결과 수신대기', SMS_KIND='S' where MSGID=?";
									msgud = conn.prepareStatement(msgudstr);
									msgud.setString(1, msg_id);
									msgud.executeUpdate();
									msgud.close();
																					
									kind = "P";
									amount = price.member_price.price_nas_sms;
									payback = price.member_price.price_nas_sms - price.parent_price.price_nas_sms;
									admin_amt = price.base_price.price_nas_sms;
									memo = "웹(B) SMS";
									if(amount == 0 || amount == 0.0f) {
										amount = admin_amt;
									}
							
									amtins = conn.prepareStatement(amtStr);
									amtins.setTimestamp(1, new java.sql.Timestamp(System.currentTimeMillis())); 
									amtins.setString(2, kind); 
									amtins.setFloat(3, amount); 
									amtins.setString(4, memo); 
									amtins.setString(5, msg_id); 
									amtins.setFloat(6, payback); 
									amtins.setFloat(7, admin_amt); 
									
									amtins.executeUpdate();
									amtins.close();
								}else if(msgtype.equals("LMS")) {
									String nasstr ="insert into cb_nas_mms_msg(SUBJECT"
											                          + ", PHONE"
											                          + ", CALLBACK"
											                          + ", STATUS"
											                          + ", MSG"
											                          + ", BILL_ID"
											                          + ", TYPE"
											                          + ", ETC1"
											                          + ", ETC2"
											                          + ", ETC4"
											                          + ", REQDATE) "
											                    + "values( ?"
											                          + ", ?"
											                          + ", ?"
											                          + ", ? "
											                          + ", ?"
											                          + ", ?"
											                          + ", ?"
											                          + ", ?"
											                          + ", ?"
											                          + ", ?"
											                          + ", ?)";
									PreparedStatement nasins = conn.prepareStatement(nasstr);
									nasins.setString(1,  rs.getString("SMS_LMS_TIT").replaceAll("\\r\\n|\\r|\\n", ""));
									nasins.setString(2, phn);
									nasins.setString(3, rs.getString("SMS_SENDER"));
									nasins.setString(4, "0");
									nasins.setString(5, msg_sms);
									nasins.setString(6, rs.getString("SMS_SENDER"));
									nasins.setString(7, "0");
									nasins.setString(9, msg_id);
									nasins.setString(10, sent_key);
									nasins.setString(12, mem_id);
									if(rs.getString("RESERVE_DT").equals("00000000000000")) {
										nasins.setString(13, rd.format(reserve_dt));
									}else {
										nasins.setString(13, rs.getString("RESERVE_DT"));
									}
									nasins.executeUpdate();
									nasins.close();
									
									wtudstr = "update cb_wt_msg_sent set mst_nas=ifnull(mst_nas,0)+1 where mst_id=?";
									wtud = conn.prepareStatement(wtudstr);
									wtud.setString(1, sent_key);
									wtud.executeUpdate();
									wtud.close();
									
									msgudstr = "update cb_msg_" + userid + " set MESSAGE_TYPE='ns',CODE='NAS', MESSAGE = '결과 수신대기' where MSGID=?";
									msgud = conn.prepareStatement(msgudstr);
									msgud.setString(1, msg_id);
									msgud.executeUpdate();
									msgud.close();
														
									kind = "P";
									amount = price.member_price.price_nas;
									payback = price.member_price.price_nas - price.parent_price.price_nas;
									admin_amt = price.base_price.price_nas;
									memo = "웹(B)";
									if(amount == 0 || amount == 0.0f) {
										amount = admin_amt;
									}
						
									amtins = conn.prepareStatement(amtStr);
									amtins.setTimestamp(1, new java.sql.Timestamp(System.currentTimeMillis())); 
									amtins.setString(2, kind); 
									amtins.setFloat(3, amount); 
									amtins.setString(4, memo); 
									amtins.setString(5, msg_id); 
									amtins.setFloat(6, payback); 
									amtins.setFloat(7, admin_amt); 
									
									amtins.executeUpdate();
									amtins.close();
								}
								wtudstr = "update cb_wt_msg_sent set mst_nas=ifnull(mst_nas,0)+1 where mst_id=?";
								wtud = conn.prepareStatement(wtudstr);
								wtud.setString(1, sent_key);
								wtud.executeUpdate();
								wtud.close();
								
								msgudstr = "update cb_msg_" + userid + " set MESSAGE_TYPE='ns',CODE='NAS', MESSAGE = '결과 수신대기' where MSGID=?";
								msgud = conn.prepareStatement(msgudstr);
								msgud.setString(1, msg_id);
								msgud.executeUpdate();
								msgud.close();
							}

							break;
							
						}
					} else {
						// 2차 발신이 없는 경우 카카오 실패 건시 처리
						wtudstr = "";
						
						if(rs.getString("MESSAGE_TYPE").equals("ft")) {
							if(rs.getString("IMAGE_URL").isEmpty()) {
								wtudstr = "update cb_wt_msg_sent set mst_err_ft = ifnull(mst_err_ft,0)+1 where mst_id=?";
							} else {
								wtudstr = "update cb_wt_msg_sent set mst_err_ft_img = ifnull(mst_err_ft_img,0)+1 where mst_id=?";
							}
						} else if(rs.getString("MESSAGE_TYPE").equals("at")) {
							wtudstr = "update cb_wt_msg_sent set mst_err_at=ifnull(mst_err_at,0)+1 where mst_id=?";
						}
						
						if(!wtudstr.isEmpty()) {
							wtud = conn.prepareStatement(wtudstr);
							wtud.setString(1, sent_key);
							wtud.executeUpdate();
							wtud.close();
						}
					}
					
				}
				
				String trrdelstr = "delete from TBL_REQUEST_RESULT where MSGID= ?";
				PreparedStatement trrdel = conn.prepareStatement(trrdelstr);
				trrdel.setString(1, msg_id);
				trrdel.executeUpdate();
				trrdel.close();
				//break;
			}
			
			rs.close();
			
		} catch (Exception ex) {
			log.info("cb_wt_setting 조회 오류"+ex.toString());
		}
		
		try {
			if(nconn!=null) {
				nconn.close();
			}
		} catch(Exception e) {}
		
		try {
			if(tbl_result!=null) {
				tbl_result.close();
			}
		} catch(Exception e) {}

		try {
			if(conn!=null) {
				conn.close();
			}
		} catch(Exception e) {}
			
//		try {
//			//Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		TBLReqProcess.isRunning = false;
		//log.info("TBL RESULT PROC 끝");
	}
	
	 
}