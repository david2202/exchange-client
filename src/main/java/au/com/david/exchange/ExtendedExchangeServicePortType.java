package au.com.david.exchange;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Holder;

import com.microsoft.schemas.exchange.services._2006.messages.CreateItemResponseType;
import com.microsoft.schemas.exchange.services._2006.messages.CreateItemType;
import com.microsoft.schemas.exchange.services._2006.messages.DeleteItemResponseType;
import com.microsoft.schemas.exchange.services._2006.messages.DeleteItemType;
import com.microsoft.schemas.exchange.services._2006.messages.ExchangeServicePortType;
import com.microsoft.schemas.exchange.services._2006.messages.FindItemResponseType;
import com.microsoft.schemas.exchange.services._2006.messages.FindItemType;
import com.microsoft.schemas.exchange.services._2006.types.RequestServerVersion;
import com.microsoft.schemas.exchange.services._2006.types.ServerVersionInfo;

/**
 * Extended the JAX-WS generated class to overload services with optional
 * parameters. If you don't do this, then <xsi:nil> gets passed for null
 * parameters, which Exchange doesn't like.
 * 
 * @see http://blogs.msdn.com/b/dotnetinterop/archive/2008/07/29/connecting-to-exchange-using-jax-ws-part-1.aspx
 * @author howed
 * 
 */
@SuppressWarnings("restriction")
@WebService(name = "ExchangeServicePortType", targetNamespace = "http://schemas.microsoft.com/exchange/services/2006/messages")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
		com.microsoft.schemas.exchange.services._2006.messages.ObjectFactory.class,
		com.microsoft.schemas.exchange.services._2006.types.ObjectFactory.class })
public interface ExtendedExchangeServicePortType extends
		ExchangeServicePortType {

	@WebMethod(operationName = "CreateItem", action = "http://schemas.microsoft.com/exchange/services/2006/messages/CreateItem")
	public void createItem(
			@WebParam(name = "CreateItem", targetNamespace = "http://schemas.microsoft.com/exchange/services/2006/messages", partName = "request") CreateItemType request,
			@WebParam(name = "RequestServerVersion", targetNamespace = "http://schemas.microsoft.com/exchange/services/2006/types", header = true, partName = "RequestVersion") RequestServerVersion requestVersion,
			@WebParam(name = "CreateItemResponse", targetNamespace = "http://schemas.microsoft.com/exchange/services/2006/messages", mode = WebParam.Mode.OUT, partName = "CreateItemResult") Holder<CreateItemResponseType> createItemResult,
			@WebParam(name = "ServerVersionInfo", targetNamespace = "http://schemas.microsoft.com/exchange/services/2006/types", header = true, mode = WebParam.Mode.OUT, partName = "ServerVersion") Holder<ServerVersionInfo> serverVersion);

	@WebMethod(operationName = "FindItem", action = "http://schemas.microsoft.com/exchange/services/2006/messages/FindItem")
	public void findItem(
			@WebParam(name = "FindItem", targetNamespace = "http://schemas.microsoft.com/exchange/services/2006/messages", partName = "request") FindItemType request,
			@WebParam(name = "RequestServerVersion", targetNamespace = "http://schemas.microsoft.com/exchange/services/2006/types", header = true, partName = "RequestVersion") RequestServerVersion requestVersion,
			@WebParam(name = "FindItemResponse", targetNamespace = "http://schemas.microsoft.com/exchange/services/2006/messages", mode = WebParam.Mode.OUT, partName = "FindItemResult") Holder<FindItemResponseType> findItemResult,
			@WebParam(name = "ServerVersionInfo", targetNamespace = "http://schemas.microsoft.com/exchange/services/2006/types", header = true, mode = WebParam.Mode.OUT, partName = "ServerVersion") Holder<ServerVersionInfo> serverVersion);

	/**
	 * 
	 * @param request
	 * @param serverVersion
	 * @param impersonation
	 * @param requestVersion
	 * @param mailboxCulture
	 * @param deleteItemResult
	 */
	@WebMethod(operationName = "DeleteItem", action = "http://schemas.microsoft.com/exchange/services/2006/messages/DeleteItem")
	public void deleteItem(
			@WebParam(name = "DeleteItem", targetNamespace = "http://schemas.microsoft.com/exchange/services/2006/messages", partName = "request") DeleteItemType request,
			@WebParam(name = "RequestServerVersion", targetNamespace = "http://schemas.microsoft.com/exchange/services/2006/types", header = true, partName = "RequestVersion") RequestServerVersion requestVersion,
			@WebParam(name = "DeleteItemResponse", targetNamespace = "http://schemas.microsoft.com/exchange/services/2006/messages", mode = WebParam.Mode.OUT, partName = "DeleteItemResult") Holder<DeleteItemResponseType> deleteItemResult,
			@WebParam(name = "ServerVersionInfo", targetNamespace = "http://schemas.microsoft.com/exchange/services/2006/types", header = true, mode = WebParam.Mode.OUT, partName = "ServerVersion") Holder<ServerVersionInfo> serverVersion);
}
