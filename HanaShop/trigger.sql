CREATE TRIGGER trg_TotalPriceInAItem ON dbo.ItemsInCart AFTER UPDATE AS
BEGIN
	UPDATE dbo.ItemsInCart SET TotalPrice = Quantity*Price
	FROM dbo.ItemsInCart
	;

END
GO
------------------
CREATE TRIGGER trg_TotalInCart ON dbo.ItemsInCart AFTER UPDATE, INSERT AS
BEGIN
	UPDATE dbo.Cart SET Total 
	= (SELECT SUM(TotalPrice) 
		FROM dbo.ItemsInCart
		WHERE CartID = dbo.Cart.CartID
		)
	;

	
END
GO
----------------------------------------------------

---------------------------------------------------------------
CREATE TRIGGER trg_SetStatusFood ON dbo.FoodAndDrink AFTER UPDATE AS
BEGIN
	UPDATE dbo.FoodAndDrink SET Status='Inactive' 
	WHERE Quantity = 0
	;
	-- if update status Active when quantity >0 => can't set Inactive status in web
END
GO
-------------
IF OBJECT_ID('trg_SetStatusFood', 'TR') IS NOT NULL
	DROP trigger trg_SetStatusFood
GO