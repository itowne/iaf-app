package newland.iaf.base.service;

import newland.iaf.base.model.TransMsg;

public interface TransMsgService {

	TransMsg findById(String orderNo);
}
