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
IF OBJECT_ID('trg_DecreQuantityWhenUserPayment', 'TR') IS NOT NULL
	DROP trigger trg_DecreQuantityWhenUserPayment
GO
CREATE TRIGGER trg_DecreQuantityWhenUserPayment ON dbo.Cart AFTER UPDATE AS
BEGIN
	DECLARE @CartID int
	
	SELECT @CartID = Inserted FROM Inserted --set CartID
	
	SELECT FoodID FROM dbo.ItemsInCart WHERE CartID=@CartID


	
END
GO
