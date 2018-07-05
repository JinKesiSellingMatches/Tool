package core.Tool.rocketEQ;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendCallback;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

public class RocketMQSend {

	// 发送数据
	public void sendRocket(RocketEQContentPOJO rocketEQContent)
			throws MQClientException, RemotingException, InterruptedException {

		DefaultMQProducer producer = new DefaultMQProducer("WECHAT_TUNNEL_ACCEPT_GROUP");

		//producer.setNamesrvAddr("139.196.156.250:9876");

		producer.setNamesrvAddr("127.0.0.1:9876");
		
		producer.start();
		try {

			// byte[] body=RaceUtils.writeKryoObject(rocketEQContent);a7d50ca6ae189DF
//			StringBuffer sBuffer = new StringBuffer("");
//			sBuffer.append(
//					"<Document><MsgHdr><MsgCgy>UNIP</MsgCgy><TstFlg>P</TstFlg><VerId>1.10</VerId><SndrSysId>sp_payment</SndrSysId><RcvrSysId>adapter</RcvrSysId><CreDt>2018-04-17T11:56:46</CreDt><MsgDefIdr>trade.001.01</MsgDefIdr><Prty>3</Prty><MsgDirc>O</MsgDirc><PssblDplct>true</PssblDplct></MsgHdr><UPMBody><MsgId>f6cd8fcb784c44f4aa3588c72df3bc44</MsgId><TotOrdId>ddpcno20154521172031</TotOrdId><OrderId>ddpcno20154521172031201</OrderId>");
//			sBuffer.append("<TxId>X20180426111643930641776</TxId>");
//			sBuffer.append("<TxTp>01</TxTp>");
//			sBuffer.append(
//					"<PmtTp>96</PmtTp><PmtChnlTp>02</PmtChnlTp><WakeUpMode>01</WakeUpMode><PcsMode>01</PcsMode><AcctIntfCd>CS002</AcctIntfCd><ExAccountInfo><L1CWD>DBTR</L1CWD><L2CWD>CDTR</L2CWD></ExAccountInfo><OrgnlBizInfo/>");
//			sBuffer.append("<ExMsgInfo><ExTxId>a7d50ca6ae189DF</ExTxId></ExMsgInfo>");
//			sBuffer.append(
//					"<Chnl><ChnlId>10000009</ChnlId><ReqSrcTp>02</ReqSrcTp><AcqId>03</AcqId><ChnlRetUrl>http://www.eigpay.com</ChnlRetUrl><ChnlNotfUrl>http://36.110.99.253:8888/paynotify/GetPayNotify</ChnlNotfUrl><POI><MrchId>z1008611</MrchId><SchMrchNm>wanglanhuashop</SchMrchNm><ProdId>1321321</ProdId><ProdNm>1</ProdNm><ProdInfo>电商商品</ProdInfo><ProdQty>1</ProdQty></POI><ChnlReqIp>127.0.0.1</ChnlReqIp></Chnl><Amt><TxAmt Ccy=\"CNY\">0.01</TxAmt><CuTxAm Ccy=\"CNY\">0.01</CuTxAm><TxScore Ccy=\"CNY\">0.00</TxScore></Amt><DateInfo><OrgLocDtTm>2018-04-17T11:56:46</OrgLocDtTm><TxDtTm>2018-04-17T11:56:46</TxDtTm><RcvTm>2018-04-04T17:21:22</RcvTm><TrcTm>2018-04-17T11:56:47</TrcTm><StrtTm>2018-04-17T11:56:46</StrtTm><EndTm>2018-04-17 03:56:47.588 UTC</EndTm><ExecTm>2018-04-17 03:56:46.547 UTC</ExecTm></DateInfo><MOP><AsgnMOP><CdtrId>wechat</CdtrId></AsgnMOP><CalMOP><CdtrId>wechat</CdtrId><TunMerId>1487994242</TunMerId><UserNm>wx426fe60c67188d74</UserNm><TunVieUrl>general/espcashier/thirdWechatAppPay</TunVieUrl><TunNotUrl>www.baidu.com</TunNotUrl><TunRetUrl>www.baidu.com</TunRetUrl><TunGwUrl>www.baidu.com</TunGwUrl></CalMOP></MOP><DbtrInf><Dbtr><Nm>TAC</Nm><CustId>88888888</CustId></Dbtr><DbtrAcct><Id>800000000100030001</Id><SubTp>BR0003</SubTp></DbtrAcct></DbtrInf><CdtrInf><Cdtr><Nm>wanglanhuashop</Nm><CustId>226602000008808009535</CustId></Cdtr><CdtrAcct><Id>200000000300020001</Id><SubTp>BR0002</SubTp><AcctSrcId>1487994242</AcctSrcId></CdtrAcct></CdtrInf><CustInfo><CustInfoDtl><Id>226601000008808006500</Id><CustNm>身份证</CustNm><CustLoginCode>lixiao</CustLoginCode><CustLoginTp>01</CustLoginTp></CustInfoDtl><AcctInfo><AcctID></AcctID><On-Shore>false</On-Shore><Tp>101</Tp></AcctInfo><CardInfo><CardTp>03</CardTp><CardSeqNb></CardSeqNb><CardBkCode>wechat</CardBkCode><CardBkNm></CardBkNm></CardInfo><Authntcn><MobNb>18131372222</MobNb><Passwd></Passwd></Authntcn></CustInfo><MerchantInfo><MerInfoDtl><MrchId>z1008611</MrchId><Id>226602000008808009535</Id><MerName>wanglanhuashop</MerName></MerInfoDtl></MerchantInfo><PlatFormInfo><PlatFormInfoDtl><Id>226601000000000000006</Id><PlatFormId>99999</PlatFormId><PlatFormName>企业支付平台</PlatFormName></PlatFormInfoDtl></PlatFormInfo><MndtInf/><CmtInf><Remark>备注信息...</Remark></CmtInf><PrcInf><MsgSts>01</MsgSts><ChkSts>01</ChkSts><SttlmDt>2018-04-17T11:56:46</SttlmDt></PrcInf><Exn3></Exn3><Exn4>N</Exn4></UPMBody></Document>");
//			String body = sBuffer.toString();
//			String body="<Document><MsgHdr><MsgCgy>UNIP</MsgCgy><TstFlg>P</TstFlg><VerId>1.10</VerId><SndrSysId>sp_paytrans</SndrSysId><RcvrSysId>sp_payment</RcvrSysId><CreDt>2018-06-20T18:24:33</CreDt><MsgDefIdr>trade.001.01</MsgDefIdr><Prty>3</Prty><MsgDirc>O</MsgDirc><PssblDplct>false</PssblDplct></MsgHdr><UPMBody><MsgId>44d2cf0cadbb45f180ed6d9c42c84405</MsgId><TotOrdId>ddpcno30151584551156</TotOrdId><OrderId>ddpcno30151584551156201</OrderId><TxTp>20</TxTp><PmtTp>96</PmtTp><PmtChnlTp>02</PmtChnlTp><BizTp>13</BizTp><TntInstId>0000</TntInstId><WakeUpMode>01</WakeUpMode><PcsMode>01</PcsMode><Language>en_us</Language><ExcheRae>1.00</ExcheRae><Chnl><ChnlId>10000009</ChnlId><ReqSrcTp>02</ReqSrcTp><AcqId>03</AcqId><ChnlRetUrl>http://www.eigpay.com</ChnlRetUrl><ChnlNotfUrl>http://36.110.99.253:8888/paynotify/GetPayNotify</ChnlNotfUrl><POI><MrchId>TAC0001</MrchId><ProdId>1321321</ProdId><ProdNm>1</ProdNm><ProdInfo>电商商品</ProdInfo><ProdQty>1</ProdQty></POI><ChnlReqIp>127.0.0.1</ChnlReqIp></Chnl><Amt><TxAmt Ccy=\"USD\">1.00</TxAmt><CuTxAm Ccy=\"USD\">1.00</CuTxAm><TxScore Ccy=\"USD\">0.00</TxScore></Amt><DateInfo><RcvTm>2018-04-04T17:21:26</RcvTm><StrtTm>2018-06-20T18:24:33</StrtTm></DateInfo><MOP><AsgnMOP><CdtrId>alipay</CdtrId></AsgnMOP></MOP><CustInfo><CustInfoDtl><CustLoginCode>lixiao</CustLoginCode><CustLoginTp>01</CustLoginTp></CustInfoDtl><AcctInfo><AcctID></AcctID><On-Shore>false</On-Shore><Tp>101</Tp></AcctInfo><CardInfo><CardSeqNb></CardSeqNb><CardBkCode>alipay</CardBkCode><CardBkNm></CardBkNm></CardInfo><Authntcn><Passwd></Passwd></Authntcn></CustInfo><MerchantInfo><ItemMersInfoList><ItemMrchId>y1234567</ItemMrchId><ItemTxAmt Ccy=\"USD\">1.00</ItemTxAmt><ItemProdNm>ticket etc</ItemProdNm><ItemProdQty>3</ItemProdQty><ItemProdInfo>hotel and ticket</ItemProdInfo></ItemMersInfoList></MerchantInfo><CmtInf><Remark>备注信息...</Remark></CmtInf><Exn3></Exn3><Exn4>N</Exn4></UPMBody></Document>";
			//Q_Integration_To_Adapter_Wechat_Query
			//Q_Integration_To_Adapter_Wechat
			
			
//			日切
			String body="<msg><req_sys_id>scheduleCenter</req_sys_id>"
					+ "<serv_sys_id>Q_alipay_Download_Topic</serv_sys_id>"
					+ "<event_code>Q_alipay_Download_Topic</event_code>"
					+ "<task_date>20180626</task_date>"
					+ "<trace_id>cut_off_handler_105</trace_id><exec_num>0</exec_num><batch_id>105</batch_id><return_code>0000</return_code></msg>";
			
			/**
			 * 
			 * Q_Cut_Off_Day_Topic
			 * Q_Txn_Clearing_Topic
			 * Q_Separate_Accounts_Topic
			 * 微信
			 * Q_wechat_Download_Topic
			 * Q_wechat_Analy_Topic
			 * 支付宝
			 * Q_alipay_Download_Topic
			 * Q_alipay_Analy_Topic
			 * 
			 * 
			 * Q_wechat_Balance_Acc_Topic
			 * Q_alipay_Balance_Acc_Topic
			 * 
			 * Q_gopay_Download_Topic
			 * Q_gopay_Analy_Topic
			 * 
			 * Q_Tunnel_download_To_Adapter_Gopay
			 */
			
			Message msgTobroker = new Message("Q_alipay_Download_Topic", body.getBytes());

			producer.send(msgTobroker, new SendCallback() {

				@Override
				public void onSuccess(SendResult sendResult) {
					System.out.println(sendResult.getMsgId());
				}

				public void onException(Throwable e) {
					// TODO 这里需要日志记录失败
				}
			});
			producer.shutdown();

		} catch (Exception e) {
			// TODO 日志
			System.out.println(e);
			producer.shutdown();
		}
	}

	public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException {
		RocketMQSend rocketEQSend = new RocketMQSend();
		rocketEQSend.sendRocket(new RocketEQContentPOJO());
	}

}
