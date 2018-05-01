package com.bwf.p2p.biz.interfaces;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.bwf.p2p.pojos.Apply;
import com.bwf.p2p.pojos.Product;
import com.bwf.p2p.pojos.Sysuser;
import com.bwf.p2p.vo.IndexVoInnerProduct;

public interface IndexService {


	Set<String> getAllLendingPeriods();

	Set<IndexVoInnerProduct> getAllIndexVoInnerProduct();

	Set<String> getProducttypes();

	Product getProductById(int productId);

	int insertOneApply(@Valid Apply apply);

	int insertOneUser(Sysuser sysuser);

	int getIdByUserName(String userName);

	Sysuser selectOneUser(Sysuser sysuser);

	Set<IndexVoInnerProduct> getIndexVoInnerProductByPageIndex(int top, int bottom);

	int selectTotalItemCount();


	List<IndexVoInnerProduct> getIndexVoInnerProductBySearch(String productTypeName, String period,
			Float primeLendingRateFrom, Float primeLendingRateTo, Integer financingAmountFrom,
			Integer financingAmountTo);



}
