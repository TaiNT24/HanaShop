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