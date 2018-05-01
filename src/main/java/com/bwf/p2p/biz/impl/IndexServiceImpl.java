package com.bwf.p2p.biz.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bwf.p2p.biz.interfaces.IndexService;
import com.bwf.p2p.mapper.ApplyMapper;
import com.bwf.p2p.mapper.LendingPeriodMapper;
import com.bwf.p2p.mapper.ProductMapper;
import com.bwf.p2p.mapper.ProducttypeMapper;
import com.bwf.p2p.mapper.SysuserMapper;
import com.bwf.p2p.pojos.Apply;
import com.bwf.p2p.pojos.Product;
import com.bwf.p2p.pojos.Sysuser;
import com.bwf.p2p.vo.IndexVoInnerProduct;

@Service("indexService")
public class IndexServiceImpl implements IndexService{   
    @Autowired
	private LendingPeriodMapper lendingPriodMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProducttypeMapper producttypeMapper;
    @Autowired
    private ApplyMapper applyMapper;
    @Autowired
    private SysuserMapper sysuserMapper;
    
  //  private static final Logger logger=LoggerFactory.getLogger(com.bwf.p2p.biz.impl.IndexServiceImpl.class);
	@Override
	public Set<String> getAllLendingPeriods() {
		Set<String> lendingPeriods=lendingPriodMapper.getAllLendingPeriod();
		return lendingPeriods;
	}
	//没有分页的查询
	@Override
	public Set<IndexVoInnerProduct> getAllIndexVoInnerProduct() {
		Set<IndexVoInnerProduct> indexVoInnerProducts=new HashSet<>();
		Set<Product> products=productMapper.getProductWithProductType();
		Iterator<Product> it=products.iterator();
		while(it.hasNext()) {
			Product pro=it.next();
			IndexVoInnerProduct ivp=new IndexVoInnerProduct();
			ivp.setProduct(pro);
			ivp.setCompanyName(pro.getCompany().getCompanyName());
			ivp.setProductTypeName(pro.getProducttype().getProductTypeName());
			ivp.setPeriod(pro.getLendingPeriod().getPeriod());
			indexVoInnerProducts.add(ivp);
		}
		return indexVoInnerProducts;
	}
	//分页查询
	@Override
	public Set<IndexVoInnerProduct> getIndexVoInnerProductByPageIndex(int top, int bottom) {
		Set<IndexVoInnerProduct> indexVoInnerProducts=new HashSet<>();
		Set<Product> products=productMapper.getProductWithAssociations(top,bottom);
		Iterator<Product> it=products.iterator();
		while(it.hasNext()) {
			Product pro=it.next();
			IndexVoInnerProduct ivp=new IndexVoInnerProduct();
			ivp.setProduct(pro);
			ivp.setCompanyName(pro.getCompany().getCompanyName());
			ivp.setProductTypeName(pro.getProducttype().getProductTypeName());
			ivp.setPeriod(pro.getLendingPeriod().getPeriod());
			indexVoInnerProducts.add(ivp);
		}
		return indexVoInnerProducts;		
	}
	//总共有多少数据
	@Override
	public int selectTotalItemCount() {
		int totalItemCounts=productMapper.getTotalItemCount();
		return totalItemCounts;
	}	
	@Override
	public Set<String> getProducttypes() {
		Set<String> producttypes= producttypeMapper.getAllProductTypeName();
		return producttypes ;
	}
	@Override
	public Product getProductById(int productId) {
		Product pro=productMapper.getProductById(productId);
		//logger.info("记日志{}", productId);
		System.out.println("sssss");
		return pro;
	}
	@Override
	public int insertOneApply(@Valid Apply apply) {
		int row=applyMapper.insertOneApply(apply);
		return row;
	}
	@Override
	public int insertOneUser(Sysuser sysuser) {
		int row=sysuserMapper.insertOneUser();
		return row;
	}
	@Override
	public int getIdByUserName(String userName) {
		int row=sysuserMapper.getIdByUserName();
		return row;
	}
	@Override
	public Sysuser selectOneUser(Sysuser sysuser) {
		Sysuser user=sysuserMapper.selectOneUser(sysuser);
		return user;
	}
	@Override
	public List<IndexVoInnerProduct> getIndexVoInnerProductBySearch(String productTypeName, String period,
			Float primeLendingRateFrom, Float primeLendingRateTo, Integer financingAmountFrom,
			Integer financingAmountTo) {
		List<IndexVoInnerProduct> ivips=new ArrayList<>();
		List<Product> pros=productMapper.getIndexVoInnerProductBySearch(productTypeName,period,primeLendingRateFrom,primeLendingRateTo,financingAmountFrom,financingAmountTo);
		Iterator< Product> its=pros.iterator();
		while(its.hasNext()) {
			Product pro=its.next();
			IndexVoInnerProduct ivip=new IndexVoInnerProduct();
			ivip.setProduct(pro);
			ivip.setCompanyName(pro.getCompany().getCompanyName());
			ivip.setPeriod(pro.getLendingPeriod().getPeriod());
			ivip.setProductTypeName(pro.getProducttype().getProductTypeName());
			ivips.add(ivip);
		}
		return ivips;
	}


	
	
	
	
	
	
	
	
	
	
}
