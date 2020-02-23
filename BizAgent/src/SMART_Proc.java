import java.sql.*;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.apache.commons.lang3.StringUtils;
import com.mysql.jdbc.Driver;
import java.util.Date;

import javax.net.ssl.SSLEngineResult.Status;

public class SMART_Proc implements Runnable {

	private String DB_URL;
	
	//public static boolean isRunning = false;
	public Logger log;
	public String monthStr;
	static public boolean isRunning;
	public String div_str;
	static int proc_cnt;

	public boolean isRefund  ;

	public SMART_Proc(Logger _log) {
		log = _log;

		Date month = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMddhhmmss");
		div_str = transFormat.format(month);

	}
	
	public void run() {
		if(!SMART_Proc.isRunning) {
			if(monthStr == null || monthStr.isEmpty()) {
				Date month = new Date();
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMM");
				monthStr = transFormat.format(month);
			}
				
			Proc();
		}
	}
	
	private synchronized  void Proc() {
	 
		SMART_Proc.isRunning = true;
		
		Connection conn = null;
		Statement smtsms_msg = null;
		Statement smtmms_msg = null;
		int smstotalcnt = 0;
		int mmstotalcnt = 0;
		
		String SMSTable = "OShotSMS_" + monthStr;
		String MMSTable = "OShotMMS_" + monthStr;
		
		try {
 
			conn = BizDBCPInit.getConnection();
 
			smtsms_msg = conn.createStatement();
			
			/*
			 * SMS 6시간 지나면 자동 완료 처리 함.
			 */
			String sms_autostr = "UPDATE OShotSMS SET SendDT=now(), SendResult='6', Telecom='000' WHERE SendResult=1 and date_add(insertdt, interval 6 HOUR) < now()";
			PreparedStatement sms_auto_finish = conn.prepareStatement(sms_autostr);
			sms_auto_finish.executeUpdate();
			sms_auto_finish.close(); 
			
			String sms_exp_str = "SELECT * FROM OShotSMS WHERE SendResult>1 AND SendDT is not null and telecom = '000'";
			ResultSet sms_rs = smtsms_msg.executeQuery(sms_exp_str);
			while(sms_rs.next()) {
				String sms_movestr = "INSERT INTO " + SMSTable + "(MsgID, Sender, Receiver, Msg, URL, ReserveDT, TimeoutDT, SendDT, SendResult, Telecom, InsertDT, mst_id, proc_flag, cb_msg_id) " +
									"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement sms_movest = conn.prepareStatement(sms_movestr);
				sms_movest.setString(0, sms_rs.getString(0));
				sms_movest.setString(1, sms_rs.getString(1));
				sms_movest.setString(2, sms_rs.getString(2));
				sms_movest.setString(3, sms_rs.getString(3));
				sms_movest.setString(4, sms_rs.getString(4));
				sms_movest.setString(5, sms_rs.getString(5));
				sms_movest.setString(6, sms_rs.getString(6));
				sms_movest.setString(7, sms_rs.getString(7));
				sms_movest.setString(8, sms_rs.getString(8));
				sms_movest.setString(9, sms_rs.getString(9));
				sms_movest.setString(10, sms_rs.getString(10));
				sms_movest.setString(11, sms_rs.getString(11));
				sms_movest.setString(12, sms_rs.getString(12));
				sms_movest.setString(13, sms_rs.getString(13));
				
				sms_movest.executeUpdate();
				sms_movest.close();
				
				String sms_delstr = "DELETE d FROM OShotSMS d WHERE d.msg_id = ?";
				PreparedStatement sms_delst = conn.prepareStatement(sms_delstr);
				sms_delst.setString(0, sms_rs.getString("MsgID"));
				sms_delst.executeUpdate();
				sms_delst.close(); 
					
			}
				
			sms_rs.close();
			
			/*
			 * MMS 6시간 지나면 자동 성공 처리 함.
			 */
			String mms_autostr = "UPDATE OShotMMS SET SendDT=now(), SendResult='6', Telecom='000' WHERE SendResult=1 and date_add(insertdt, interval 6 HOUR) < now()";
			PreparedStatement mms_auto_finish = conn.prepareStatement(mms_autostr);
			mms_auto_finish.executeUpdate();
			mms_auto_finish.close(); 
			
			String mms_exp_str = "SELECT * FROM OShotMMS WHERE SendResult>1 AND SendDT is not null and telecom = '000'";
			ResultSet mms_rs = smtsms_msg.executeQuery(mms_exp_str);
			while(mms_rs.next()) {
				String mms_movestr = "INSERT INTO " + MMSTable + "(MsgID, MsgGroupID, Sender, Receiver, Subject, Msg, ReserveDT, TimeoutDT, SendDT, SendResult, Telecom, File_Path1, File_Path2, File_Path3, File_Path4, InsertDT, mst_id, proc_flag, cb_msg_Id) " +
									"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement mms_movest = conn.prepareStatement(mms_movestr);
				mms_movest.setString(0, mms_rs.getString(0));
				mms_movest.setString(1, mms_rs.getString(1));
				mms_movest.setString(2, mms_rs.getString(2));
				mms_movest.setString(3, mms_rs.getString(3));
				mms_movest.setString(4, mms_rs.getString(4));
				mms_movest.setString(5, mms_rs.getString(5));
				mms_movest.setString(6, mms_rs.getString(6));
				mms_movest.setString(7, mms_rs.getString(7));
				mms_movest.setString(8, mms_rs.getString(8));
				mms_movest.setString(9, mms_rs.getString(9));
				mms_movest.setString(10, mms_rs.getString(10));
				mms_movest.setString(11, mms_rs.getString(11));
				mms_movest.setString(12, mms_rs.getString(12));
				mms_movest.setString(13, mms_rs.getString(13));
				mms_movest.setString(14, mms_rs.getString(14));
				mms_movest.setString(15, mms_rs.getString(15));
				mms_movest.setString(16, mms_rs.getString(16));
				mms_movest.setString(17, mms_rs.getString(17));
				mms_movest.setString(18, mms_rs.getString(18));
				
				mms_movest.executeUpdate();
				mms_movest.close();
				
				String mms_delstr = "DELETE d FROM OShotMMS d WHERE d.msg_id = ?";
				PreparedStatement mms_delst = conn.prepareStatement(mms_delstr);
				mms_delst.setString(0, mms_rs.getString("MsgID"));
				mms_delst.executeUpdate();
				mms_delst.close(); 
					
			}
						
			mms_rs.close();
			
			String sms_str = "SELECT SQL_NO_CACHE " + 
							 "       a.MsgID " + 
							 "      ,a.SendResult" + 
							 "      ,a.Receiver AS PHN" + 
							 "      ,b.mst_id AS REMARK4" + 
							 "      ,(select mem_userid from cb_member cm where cm.mem_id = b.mst_mem_id) AS mem_userid " + 
							 "      ,b.mst_mem_id AS mem_id" + 
							 "      ,a.cb_msg_id " + 
							 " from " + SMSTable + " a INNER JOIN " + 
							 "        cb_wt_msg_sent b ON a.mst_id = b.mst_id " + 
							 "WHERE a.proc_flag = 'Y' " +
							 " order by a.mst_id" ;
				
			ResultSet rs = smtsms_msg.executeQuery(sms_str);
			
			String pre_mem_id = "";
			Price_info price = null;
			
			
			while(rs.next()) {
				smstotalcnt++;

				String mem_id = rs.getString("mem_id");
				String sent_key = rs.getString("REMARK4");
				String userid = rs.getString("mem_userid");
				
				String wtudstr;
				String msgudstr;
				PreparedStatement wtud;
				PreparedStatement msgud; 
				
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
				
				if(pre_mem_id != mem_id) {
					price = new Price_info(DB_URL, Integer.valueOf(mem_id));
					pre_mem_id = mem_id;
				}
				
				int mst_grs_biz_qty = 0;
				
				// 성공 혹은 5일이 지나 기간만료 오류는 성공 처리 함.
				if(rs.getString("SendResult").equals("6") || !this.isRefund) {
					
					wtudstr = "update cb_wt_msg_sent set mst_smt = ifnull(mst_smt,0) + 1, mst_wait = mst_wait - 1 where mst_id=?";
					wtud = conn.prepareStatement(wtudstr);
					wtud.setString(1, sent_key);
					wtud.executeUpdate();
					wtud.close();
					
					msgudstr = "update cb_msg_" + userid + " set MESSAGE_TYPE='sm', MESSAGE = '웹(C) 성공', RESULT = 'Y' "
							+ " where remark4=? and msgid = ?";
					msgud = conn.prepareStatement(msgudstr);
					msgud.setString(1, sent_key);
					msgud.setString(2, rs.getString("cb_msg_id"));
					msgud.executeUpdate();
					msgud.close();
				} else if(this.isRefund){
					wtudstr = "update cb_wt_msg_sent set mst_err_smt = ifnull(mst_err_smt,0) + 1, mst_wait = mst_wait - 1  where mst_id=?";
					wtud = conn.prepareStatement(wtudstr);
					wtud.setString(1, sent_key);
					wtud.executeUpdate();
					wtud.close();
					
					msgudstr = "update cb_msg_" + userid + " set MESSAGE_TYPE='sm', MESSAGE = ?, RESULT = 'N' "
							+ " where remark4=? and msgid = ?";
					msgud = conn.prepareStatement(msgudstr);
					msgud.setString(1, rs.getString("SendResult"));
					msgud.setString(2, sent_key);
					msgud.setString(3, rs.getString("cb_msg_id"));
					msgud.executeUpdate();
					msgud.close();
										
					kind = "3";

					amount = price.member_price.price_smt_sms;
					payback = price.member_price.price_smt_sms - price.parent_price.price_smt_sms;
					admin_amt = price.base_price.price_smt_sms;
					memo = "웹(C) 발송실패 환불";

					if(amount == 0 || amount == 0.0f) {
						amount = admin_amt;
					}

					amtins = conn.prepareStatement(amtStr);
					amtins.setTimestamp(1, new java.sql.Timestamp(System.currentTimeMillis())); 
					amtins.setString(2, kind); 
					amtins.setFloat(3, amount); 
					amtins.setString(4, memo); 
					amtins.setString(5, rs.getString("cb_msg_id") + "_" + rs.getString("PHN")); 
					amtins.setFloat(6, payback * -1 ); 
					amtins.setFloat(7, admin_amt * -1 ); 
					
					amtins.executeUpdate();
					amtins.close();
					
				} 

				String smtsms_st_ud = "update " + SMSTable + " set proc_flag = 'N' where msgid = ?";
				PreparedStatement st_smtsms_st_ud = conn.prepareStatement(smtsms_st_ud);
				st_smtsms_st_ud.setInt(1,  rs.getInt("MsgID"));
				st_smtsms_st_ud.executeUpdate();
				st_smtsms_st_ud.close();
			
			}
			rs.close();
			

			 
			smtmms_msg = conn.createStatement();
			
			String mms_str = "SELECT SQL_NO_CACHE " + 
							 "       a.MsgID " + 
							 "      ,a.SendResult" + 
							 "      ,a.Receiver AS PHN" + 
							 "      ,b.mst_id AS REMARK4" + 
							 "      ,(select mem_userid from cb_member cm where cm.mem_id = b.mst_mem_id) AS mem_userid" + 
							 "      ,b.mst_mem_id AS mem_id" + 
							 "      ,a.cb_msg_id " + 
							 "      ,a.File_Path1 as mms1 " + 
							 "      ,a.File_Path2 as mms2 " + 
							 "      ,a.File_Path3 as mms3 " + 
							 " from " + MMSTable + " a INNER JOIN " + 
							 "        cb_wt_msg_sent b ON a.mst_id = b.mst_id " + 
							 "WHERE a.proc_flag = 'Y' " +
							 " order by a.mst_id" ;
				
			ResultSet mmsrs = smtmms_msg.executeQuery(mms_str);
			
			pre_mem_id = "";
			price = null;
			
			
			while(mmsrs.next()) {
				mmstotalcnt++;

				String mem_id = mmsrs.getString("mem_id");
				String sent_key = mmsrs.getString("REMARK4");
				String userid = mmsrs.getString("mem_userid");
				
				String wtudstr;
				String msgudstr;
				PreparedStatement wtud;
				PreparedStatement msgud; 
				
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
				
				if(pre_mem_id != mem_id) {
					price = new Price_info(DB_URL, Integer.valueOf(mem_id));
					pre_mem_id = mem_id;
				}
				
				if(mmsrs.getString("SendResult").equals("6") || !this.isRefund) {
					
					wtudstr = "update cb_wt_msg_sent set mst_smt = ifnull(mst_smt,0) + 1, mst_wait = mst_wait - 1 where mst_id=?";
					wtud = conn.prepareStatement(wtudstr);
					wtud.setString(1, sent_key);
					wtud.executeUpdate();
					wtud.close();
					
					msgudstr = "update cb_msg_" + userid + " set MESSAGE_TYPE='sm', MESSAGE = '웹(C) 성공', RESULT = 'Y' "
							+ " where remark4=? and msgid = ?";
					msgud = conn.prepareStatement(msgudstr);
					msgud.setString(1, sent_key);
					msgud.setString(2, mmsrs.getString("cb_msg_id"));
					msgud.executeUpdate();
					msgud.close();
				} else if(this.isRefund){
					wtudstr = "update cb_wt_msg_sent set mst_err_smt = ifnull(mst_err_smt,0) + 1, mst_wait = mst_wait - 1  where mst_id=?";
					wtud = conn.prepareStatement(wtudstr);
					wtud.setString(1, sent_key);
					wtud.executeUpdate();
					wtud.close();
					
					msgudstr = "update cb_msg_" + userid + " set MESSAGE_TYPE='sm', MESSAGE = ?, RESULT = 'N' "
							+ " where remark4=? and msgid = ?";
					msgud = conn.prepareStatement(msgudstr);
					msgud.setString(1, mmsrs.getString("SendResult"));
					msgud.setString(2, sent_key);
					msgud.setString(3, mmsrs.getString("cb_msg_id"));
					msgud.executeUpdate();
					msgud.close();
										
					kind = "3";

					String mms1 = mmsrs.getString("mms1");
					String mms2 = mmsrs.getString("mms2");
					String mms3 = mmsrs.getString("mms3");
					
					if( ( mms1 == null || mms1.isEmpty())  && ( mms2 == null || mms2.isEmpty()) && ( mms3 == null || mms3.isEmpty())) {
						amount = price.member_price.price_smt;
						payback = price.member_price.price_smt - price.parent_price.price_smt;
						admin_amt = price.base_price.price_smt;
						memo = "웹(C) 발송실패 환불";
					} else {
						amount = price.member_price.price_smt_mms;
						payback = price.member_price.price_smt_mms - price.parent_price.price_smt_mms;
						admin_amt = price.base_price.price_smt_mms;
						memo = "웹(C) MMS 발송실패 환불";
					}

					if(amount == 0 || amount == 0.0f) {
						amount = admin_amt;
					}

					amtins = conn.prepareStatement(amtStr);
					amtins.setTimestamp(1, new java.sql.Timestamp(System.currentTimeMillis())); 
					amtins.setString(2, kind); 
					amtins.setFloat(3, amount); 
					amtins.setString(4, memo); 
					amtins.setString(5, mmsrs.getString("cb_msg_id") + "_" + mmsrs.getString("PHN")); 
					amtins.setFloat(6, payback * -1 ); 
					amtins.setFloat(7, admin_amt * -1 ); 
					
					amtins.executeUpdate();
					amtins.close();
					
				} 

				String smtmms_st_ud = "update " + MMSTable + " set proc_flag = 'N' where msgid = ?";
				PreparedStatement st_smtmms_st_ud = conn.prepareStatement(smtmms_st_ud);
				st_smtmms_st_ud.setInt(1,  mmsrs.getInt("MsgID"));
				st_smtmms_st_ud.executeUpdate();
				st_smtmms_st_ud.close();
			
			}
			mmsrs.close();
			
		}catch(Exception ex) {
			log.info("Smart me 처리 오류 : " + ex.toString());
		}
		
		if(smstotalcnt > 0 || mmstotalcnt > 0) {
			log.info("Smart SMS " + smstotalcnt + " 건 처리 함. / " + "Smart MMS " + mmstotalcnt + " 건 처리함." );
		}
		
		try {
			if(smtsms_msg!=null) {
				smtsms_msg.close();
			}
		} catch(Exception e) {}

		try {
			if(conn!=null) {
				conn.close();
			}
		} catch(Exception e) {}
		
		SMART_Proc.isRunning = false; 
	}
	
	 
}
