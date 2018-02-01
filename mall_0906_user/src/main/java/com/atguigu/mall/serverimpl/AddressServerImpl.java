package com.atguigu.mall.serverimpl;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.mall.bean.T_MALL_ADDRESS;
import com.atguigu.mall.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.mall.server.AddressServer;
import com.atguigu.mall.service.AddressService;
import com.google.gson.Gson;

public class AddressServerImpl implements AddressServer {
	
	@Autowired
	AddressService addressService;

	@Override
	@Path("/insert")
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	@GET
	public String insertAddressByYhid(@BeanParam T_MALL_ADDRESS address) {
		int i = addressService.insertAddressByYhid(address);
		return new Gson().toJson(i);
	}

	@Override
	@Path("/update")
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	@GET
	public String updateAddressByYhid(@BeanParam T_MALL_ADDRESS address) {
		int i = addressService.updateAddressByYhid(address);
		return new Gson().toJson(i);
	}

	@Override
	@Path("/selectbyyhid")
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	@GET
	public String getAddressByYhid(@BeanParam T_MALL_USER_ACCOUNT user) {
		List<T_MALL_ADDRESS> addressList = addressService.getAddressByYhid(user);
		return new Gson().toJson(addressList);
	}

	@Override
	@Path("/selectbyaddrid")
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	@GET
	public String getAddressByid(@QueryParam("addr_id") Integer addr_id) {
		T_MALL_ADDRESS address = addressService.getAddressByid(addr_id);
		return new Gson().toJson(address);
	}

}
