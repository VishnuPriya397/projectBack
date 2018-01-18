package com.dao;

import com.model.Cart;

public interface CartDAO {
	
    //public boolean saveProductToCart(Cart cart);
	public Cart getitem(int prodId,String email);
	public  void insertCart(Cart cart);
	/*public List<Cart>listCart();
	public boolean removeCartById(int cart_id);
	public long cartsize(int userId) ;
	public double CartPrice(int userId) ;
	public Cart editCartById(int cart_id);
	public Cart getCartById(int cart_id);
    public List<Cart>listCartbyUserId(int userId);
*/
}
