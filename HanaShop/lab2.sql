USE [master]
GO
/****** Object:  Database [HanaShop]    Script Date: 03/01/2020 11:23:49 AM ******/
CREATE DATABASE [HanaShop]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'HanaShop', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\HanaShop.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'HanaShop_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\HanaShop_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [HanaShop] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [HanaShop].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [HanaShop] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [HanaShop] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [HanaShop] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [HanaShop] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [HanaShop] SET ARITHABORT OFF 
GO
ALTER DATABASE [HanaShop] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [HanaShop] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [HanaShop] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [HanaShop] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [HanaShop] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [HanaShop] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [HanaShop] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [HanaShop] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [HanaShop] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [HanaShop] SET  DISABLE_BROKER 
GO
ALTER DATABASE [HanaShop] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [HanaShop] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [HanaShop] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [HanaShop] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [HanaShop] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [HanaShop] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [HanaShop] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [HanaShop] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [HanaShop] SET  MULTI_USER 
GO
ALTER DATABASE [HanaShop] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [HanaShop] SET DB_CHAINING OFF 
GO
ALTER DATABASE [HanaShop] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [HanaShop] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [HanaShop] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [HanaShop] SET QUERY_STORE = OFF
GO
USE [HanaShop]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 03/01/2020 11:23:50 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[UserID] [varchar](50) NOT NULL,
	[Password] [varchar](25) NOT NULL,
	[FullName] [varchar](70) NOT NULL,
	[Role] [bit] NOT NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cart]    Script Date: 03/01/2020 11:23:50 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cart](
	[CartID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [varchar](50) NOT NULL,
	[Total] [float] NULL,
	[Payment] [varchar](20) NULL,
	[BuyDate] [datetime] NULL,
 CONSTRAINT [PK__Cart__51BCD797E0BE1909] PRIMARY KEY CLUSTERED 
(
	[CartID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CategoryItem]    Script Date: 03/01/2020 11:23:50 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CategoryItem](
	[Category] [varchar](30) NOT NULL,
	[Description] [varchar](100) NULL,
 CONSTRAINT [PK__Category__8517B2E1C7E5AD79] PRIMARY KEY CLUSTERED 
(
	[Category] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FoodAndDrink]    Script Date: 03/01/2020 11:23:50 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FoodAndDrink](
	[FoodID] [int] IDENTITY(1,1) NOT NULL,
	[FoodName] [varchar](50) NOT NULL,
	[Img] [varchar](50) NOT NULL,
	[Description] [varchar](200) NOT NULL,
	[Price] [float] NOT NULL,
	[Quantity] [int] NOT NULL,
	[Category] [varchar](30) NOT NULL,
	[CreateDate] [datetime] NOT NULL,
	[Status] [varchar](30) NOT NULL,
 CONSTRAINT [PK__FoodAndD__856DB3CBB9200026] PRIMARY KEY CLUSTERED 
(
	[FoodID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HistoryUpdate]    Script Date: 03/01/2020 11:23:50 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HistoryUpdate](
	[HistoryID] [int] IDENTITY(1,1) NOT NULL,
	[FoodID] [int] NOT NULL,
	[UserID] [varchar](50) NOT NULL,
	[UpdateDate] [datetime] NOT NULL,
	[Detail] [varchar](300) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[HistoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ItemsInCart]    Script Date: 03/01/2020 11:23:50 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ItemsInCart](
	[ItemID] [int] IDENTITY(1,1) NOT NULL,
	[FoodID] [int] NOT NULL,
	[Price] [float] NOT NULL,
	[Quantity] [int] NOT NULL,
	[TotalPrice] [float] NOT NULL,
	[CartID] [int] NOT NULL,
 CONSTRAINT [PK__ItemsInC__727E83EB1D730374] PRIMARY KEY CLUSTERED 
(
	[ItemID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Account] ([UserID], [Password], [FullName], [Role]) VALUES (N'admin', N'123456', N'Admin', 1)
INSERT [dbo].[Account] ([UserID], [Password], [FullName], [Role]) VALUES (N'tai', N'123456', N'Nguyen Tu Tai', 0)
INSERT [dbo].[Account] ([UserID], [Password], [FullName], [Role]) VALUES (N'tai10', N'123456', N'TaiNguyen10', 0)
INSERT [dbo].[Account] ([UserID], [Password], [FullName], [Role]) VALUES (N'tai11', N'123456', N'Tai', 0)
INSERT [dbo].[Account] ([UserID], [Password], [FullName], [Role]) VALUES (N'tai12', N'123456', N'Tu Tai', 0)
INSERT [dbo].[Account] ([UserID], [Password], [FullName], [Role]) VALUES (N'taintse130085@fpt.edu.vn', N'117430835019095438609', N'TuTai Nguyen', 0)
SET IDENTITY_INSERT [dbo].[Cart] ON 

INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (1, N'tai', 172.60000133514404, N'Cash on delivery', CAST(N'2020-02-24T21:04:43.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (14, N'tai12', 16.800000190734863, N'Cash on delivery', CAST(N'2020-02-24T21:30:02.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (24, N'tai', 22, N'Cash on delivery', CAST(N'2020-02-24T21:26:40.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (25, N'tai', 55, N'Cash on delivery', CAST(N'2020-02-24T22:47:30.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (30, N'tai', 45.200000762939453, N'Cash on delivery', CAST(N'2020-02-24T23:56:36.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (34, N'tai', 33, N'Cash on delivery', CAST(N'2020-02-25T14:22:00.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (35, N'tai', 943.8000020980835, N'Cash on delivery', CAST(N'2020-02-26T17:43:00.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (36, N'tai', 172, N'Cash on delivery', CAST(N'2020-02-27T20:49:20.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (40, N'tai12', 58.000001907348633, N'Cash on delivery', CAST(N'2020-02-26T19:50:10.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (41, N'tai12', 16.800000190734863, N'Cash on delivery', CAST(N'2020-02-26T19:50:32.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (42, N'tai12', 464.00001525878906, N'Cash on delivery', CAST(N'2020-02-26T19:51:21.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (43, N'tai12', 28, N'Cash on delivery', CAST(N'2020-02-27T12:38:44.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (44, N'tai12', 28.600000381469727, N'Cash on delivery', CAST(N'2020-02-29T19:52:27.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (45, N'tai', 21, N'Cash on delivery', CAST(N'2020-03-01T09:01:41.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (46, N'tai11', 45.800000190734863, N'Cash on delivery', CAST(N'2020-02-28T17:29:09.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (47, N'tai11', 61, N'Cash on delivery', CAST(N'2020-03-01T09:07:41.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (48, N'taintse130085@fpt.edu.vn', 10, N'Cash on delivery', CAST(N'2020-02-28T23:45:19.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (49, N'tai12', 22, N'Cash on delivery', CAST(N'2020-03-01T08:39:42.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (50, N'tai12', 17, N'Payment With Paypal', CAST(N'2020-03-01T08:41:10.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (51, N'tai12', 26, N'Cash on delivery', CAST(N'2020-03-01T09:16:13.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (52, N'tai', 15, N'Payment With Paypal', CAST(N'2020-03-01T09:02:33.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (53, N'tai', 11, N'Cash on delivery', CAST(N'2020-03-01T09:03:32.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (54, N'tai', 5, N'Payment With Paypal', CAST(N'2020-03-01T10:55:19.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (55, N'tai11', 11, N'Cash on delivery', CAST(N'2020-03-01T09:13:35.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (56, N'tai11', 4, N'Cash on delivery', CAST(N'2020-03-01T09:15:01.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (59, N'tai12', 4, N'Cash on delivery', CAST(N'2020-03-01T09:16:39.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (60, N'tai12', 25, N'Payment With Paypal', CAST(N'2020-03-01T09:25:59.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (61, N'tai12', 11, N'Cash on delivery', CAST(N'2020-03-01T09:28:34.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (62, N'tai12', 6, N'Payment With Paypal', CAST(N'2020-03-01T09:29:04.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (63, N'tai12', 1100, NULL, NULL)
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (64, N'taintse130085@fpt.edu.vn', 22, N'Cash on delivery', CAST(N'2020-03-01T10:35:28.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (65, N'taintse130085@fpt.edu.vn', 4, N'Payment With Paypal', CAST(N'2020-03-01T10:36:18.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (66, N'taintse130085@fpt.edu.vn', 500, N'Cash on delivery', CAST(N'2020-03-01T10:37:13.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (67, N'tai', 8, N'Cash on delivery', CAST(N'2020-03-01T10:56:35.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (68, N'tai', 5, N'Payment With Paypal', CAST(N'2020-03-01T10:58:18.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (69, N'tai', 10, N'Cash on delivery', CAST(N'2020-03-01T11:02:16.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (70, N'tai', 5, N'Payment With Paypal', CAST(N'2020-03-01T11:03:14.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (71, N'tai', 6, N'Payment With Paypal', CAST(N'2020-03-01T11:07:01.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (72, N'tai', 11, N'Payment With Paypal', CAST(N'2020-03-01T11:08:43.000' AS DateTime))
INSERT [dbo].[Cart] ([CartID], [UserID], [Total], [Payment], [BuyDate]) VALUES (73, N'tai', 12, N'Payment With Paypal', CAST(N'2020-03-01T11:18:05.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[Cart] OFF
INSERT [dbo].[CategoryItem] ([Category], [Description]) VALUES (N'Drink', N'The item to drink')
INSERT [dbo].[CategoryItem] ([Category], [Description]) VALUES (N'Fast Food', N'Light meal')
INSERT [dbo].[CategoryItem] ([Category], [Description]) VALUES (N'Food', N'The item to eat')
SET IDENTITY_INSERT [dbo].[FoodAndDrink] ON 

INSERT [dbo].[FoodAndDrink] ([FoodID], [FoodName], [Img], [Description], [Price], [Quantity], [Category], [CreateDate], [Status]) VALUES (1, N'Hamburger', N'food/1.jpg', N'Hamburgers are traditionally made with ground beef and served with onions, tomatoes, lettuce, ketchup, and other garnishes.', 5.8000001907348633, 87, N'Fast Food', CAST(N'2020-02-16T00:00:00.000' AS DateTime), N'Active')
INSERT [dbo].[FoodAndDrink] ([FoodID], [FoodName], [Img], [Description], [Price], [Quantity], [Category], [CreateDate], [Status]) VALUES (2, N'Pizza', N'food/2.jpg', N' It taste chewy and crisp, with marinara sauce and cheese.', 11, 78, N'Food', CAST(N'2020-02-17T00:00:00.000' AS DateTime), N'Active')
INSERT [dbo].[FoodAndDrink] ([FoodID], [FoodName], [Img], [Description], [Price], [Quantity], [Category], [CreateDate], [Status]) VALUES (3, N'Coca cola', N'food/3.jpg', N'Drink has gas, cool', 6, 47, N'Drink', CAST(N'2020-02-27T00:29:40.000' AS DateTime), N'Active')
INSERT [dbo].[FoodAndDrink] ([FoodID], [FoodName], [Img], [Description], [Price], [Quantity], [Category], [CreateDate], [Status]) VALUES (4, N'Banana', N'food/4.jpg', N'If you eat a banana every day for breakfast, roommate nickname you "the monkey."', 11, 2, N'Fast Food', CAST(N'2020-02-27T07:50:22.000' AS DateTime), N'Active')
INSERT [dbo].[FoodAndDrink] ([FoodID], [FoodName], [Img], [Description], [Price], [Quantity], [Category], [CreateDate], [Status]) VALUES (5, N'Chicken', N'food/5.jpg', N'The fat is highly concentrated on the skin. ', 10, 20, N'Food', CAST(N'2020-02-27T07:49:47.000' AS DateTime), N'Active')
INSERT [dbo].[FoodAndDrink] ([FoodID], [FoodName], [Img], [Description], [Price], [Quantity], [Category], [CreateDate], [Status]) VALUES (6, N'Soup', N'food/6.jpg', N'Hot, delicious', 7, 20, N'Food', CAST(N'2020-02-27T08:00:28.000' AS DateTime), N'Active')
INSERT [dbo].[FoodAndDrink] ([FoodID], [FoodName], [Img], [Description], [Price], [Quantity], [Category], [CreateDate], [Status]) VALUES (7, N'Fried Chicken', N'food/7.jpg', N'It''s ', 8, 8, N'Food', CAST(N'2020-02-27T08:09:37.000' AS DateTime), N'Active')
INSERT [dbo].[FoodAndDrink] ([FoodID], [FoodName], [Img], [Description], [Price], [Quantity], [Category], [CreateDate], [Status]) VALUES (16, N'Food1', N'food/8.jpg', N'Delicious', 15, 19, N'Food', CAST(N'2020-02-27T13:10:01.000' AS DateTime), N'Active')
INSERT [dbo].[FoodAndDrink] ([FoodID], [FoodName], [Img], [Description], [Price], [Quantity], [Category], [CreateDate], [Status]) VALUES (17, N'Food2', N'food/9.jpg', N'Delicious', 14, 18, N'Food', CAST(N'2020-02-27T10:11:30.000' AS DateTime), N'Active')
INSERT [dbo].[FoodAndDrink] ([FoodID], [FoodName], [Img], [Description], [Price], [Quantity], [Category], [CreateDate], [Status]) VALUES (18, N'Monster', N'food/10.jpg', N'Fowerful drink.', 6, 24, N'Drink', CAST(N'2020-02-27T13:12:25.000' AS DateTime), N'Active')
INSERT [dbo].[FoodAndDrink] ([FoodID], [FoodName], [Img], [Description], [Price], [Quantity], [Category], [CreateDate], [Status]) VALUES (19, N'New Fish''s meet', N'food/11.jpg', N'Fresh', 11, 9, N'Food', CAST(N'2020-02-27T13:13:39.000' AS DateTime), N'Active')
INSERT [dbo].[FoodAndDrink] ([FoodID], [FoodName], [Img], [Description], [Price], [Quantity], [Category], [CreateDate], [Status]) VALUES (20, N'New Food 1', N'food/12.jpg', N'Ngon ngon', 11, 9, N'Fast Food', CAST(N'2020-02-27T15:24:21.000' AS DateTime), N'Active')
INSERT [dbo].[FoodAndDrink] ([FoodID], [FoodName], [Img], [Description], [Price], [Quantity], [Category], [CreateDate], [Status]) VALUES (22, N'Lemon Sea', N'food/13.jpg', N'Cool, fresh', 4, 10, N'Drink', CAST(N'2020-02-27T21:01:30.000' AS DateTime), N'Active')
INSERT [dbo].[FoodAndDrink] ([FoodID], [FoodName], [Img], [Description], [Price], [Quantity], [Category], [CreateDate], [Status]) VALUES (23, N'Purpose Rose', N'food/14.jpg', N'Drink has no gas, cool', 8, 10, N'Drink', CAST(N'2020-03-01T10:18:40.000' AS DateTime), N'Active')
INSERT [dbo].[FoodAndDrink] ([FoodID], [FoodName], [Img], [Description], [Price], [Quantity], [Category], [CreateDate], [Status]) VALUES (24, N'Ice Island', N'food/15.jpg', N'Bring island''s air to you', 15, 10, N'Drink', CAST(N'2020-03-01T10:19:45.000' AS DateTime), N'Active')
INSERT [dbo].[FoodAndDrink] ([FoodID], [FoodName], [Img], [Description], [Price], [Quantity], [Category], [CreateDate], [Status]) VALUES (25, N'Apple', N'food/16.jpg', N'Fruit that serves as filling for a pie around Than', 2, 46, N'Fast Food', CAST(N'2020-03-01T10:21:12.000' AS DateTime), N'Active')
INSERT [dbo].[FoodAndDrink] ([FoodID], [FoodName], [Img], [Description], [Price], [Quantity], [Category], [CreateDate], [Status]) VALUES (26, N'Pasta noddle', N'food/17.jpg', N'Noddle, beef and tomato', 10, 28, N'Food', CAST(N'2020-03-01T10:22:13.000' AS DateTime), N'Active')
INSERT [dbo].[FoodAndDrink] ([FoodID], [FoodName], [Img], [Description], [Price], [Quantity], [Category], [CreateDate], [Status]) VALUES (27, N'Wrapp', N'food/18.jpg', N'Healthy and balance', 4, 50, N'Fast Food', CAST(N'2020-03-01T10:23:28.000' AS DateTime), N'Active')
INSERT [dbo].[FoodAndDrink] ([FoodID], [FoodName], [Img], [Description], [Price], [Quantity], [Category], [CreateDate], [Status]) VALUES (28, N'Rice pork BBQ', N'food/19.jpg', N'Fullfil your stomach', 6, 17, N'Food', CAST(N'2020-03-01T10:25:01.000' AS DateTime), N'Active')
INSERT [dbo].[FoodAndDrink] ([FoodID], [FoodName], [Img], [Description], [Price], [Quantity], [Category], [CreateDate], [Status]) VALUES (29, N'Strawberry fruit', N'food/20.jpg', N'Perfect for your day', 8, 20, N'Drink', CAST(N'2020-03-01T10:26:07.000' AS DateTime), N'Active')
INSERT [dbo].[FoodAndDrink] ([FoodID], [FoodName], [Img], [Description], [Price], [Quantity], [Category], [CreateDate], [Status]) VALUES (30, N'Orange Fruit', N'food/21.jpg', N'Provice vitamin C', 5, 27, N'Drink', CAST(N'2020-03-01T10:27:14.000' AS DateTime), N'Active')
INSERT [dbo].[FoodAndDrink] ([FoodID], [FoodName], [Img], [Description], [Price], [Quantity], [Category], [CreateDate], [Status]) VALUES (31, N'Whiskey type 1', N'food/22.jpg', N'Alcoholic drink', 8, 48, N'Drink', CAST(N'2020-03-01T10:28:39.000' AS DateTime), N'Active')
INSERT [dbo].[FoodAndDrink] ([FoodID], [FoodName], [Img], [Description], [Price], [Quantity], [Category], [CreateDate], [Status]) VALUES (32, N'Mixed rice', N'food/23.jpg', N'A lot of food mixed together, Rice is main', 10, 30, N'Food', CAST(N'2020-03-01T10:29:58.000' AS DateTime), N'Inactive')
SET IDENTITY_INSERT [dbo].[FoodAndDrink] OFF
SET IDENTITY_INSERT [dbo].[HistoryUpdate] ON 

INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (1, 1, N'admin', CAST(N'2020-02-26T21:07:13.000' AS DateTime), N'Quick Update: Old Status: Active, old Category: Drink')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (2, 1, N'admin', CAST(N'2020-02-26T21:07:17.000' AS DateTime), N'Quick Update: Old Status: Active, old Category: Food')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (3, 1, N'admin', CAST(N'2020-02-26T21:07:20.000' AS DateTime), N'Quick Update: Old Status: Inactive, old Category: Food')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (4, 1, N'admin', CAST(N'2020-02-26T21:07:22.000' AS DateTime), N'Quick Update: Old Status: Inactive, old Category: Drink')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (5, 1, N'admin', CAST(N'2020-02-26T21:07:26.000' AS DateTime), N'Quick Update: Old Status: Inactive, old Category: Food')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (6, 2, N'admin', CAST(N'2020-02-26T21:07:30.000' AS DateTime), N'Quick Update: Old Status: Active, old Category: Food')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (7, 2, N'admin', CAST(N'2020-02-26T21:07:35.000' AS DateTime), N'Quick Update: Old Status: Active, old Category: Drink')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (8, 2, N'admin', CAST(N'2020-02-26T21:07:38.000' AS DateTime), N'Quick Update: Old Status: Inactive, old Category: Drink')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (9, 2, N'admin', CAST(N'2020-02-26T21:07:41.000' AS DateTime), N'Quick Update: Old Status: Inactive, old Category: Drink')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (10, 2, N'admin', CAST(N'2020-02-26T21:07:47.000' AS DateTime), N'Quick Update: Old Status: Active, old Category: Drink')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (11, 1, N'admin', CAST(N'2020-02-26T21:08:05.000' AS DateTime), N'Update detail: Old DTO: Hamburger, food/1.jpg, Hamburgers are traditionally made with ground beef and served with onions, tomatoes, lettuce, ketchup, and other garnishes., 6.8, 9, Food, Active')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (12, 1, N'admin', CAST(N'2020-02-26T21:09:36.000' AS DateTime), N'Update detail: Old DTO: Hamburger, food/1.jpg, Hamburgers are traditionally made with ground beef and served with onions, tomatoes, lettuce, ketchup, and other garnishes., 4.8, 90, Food, Active')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (13, 1, N'admin', CAST(N'2020-02-26T21:09:50.000' AS DateTime), N'Quick Update: Old Status: Active, old Category: Food')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (14, 1, N'admin', CAST(N'2020-02-26T21:34:16.000' AS DateTime), N'Quick Update: Old Status: Inactive, old Category: Food')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (15, 1, N'admin', CAST(N'2020-02-26T21:34:22.000' AS DateTime), N'Quick Update: Old Status: Active, old Category: Food')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (17, 19, N'admin', CAST(N'2020-02-27T14:24:20.000' AS DateTime), N'Quick Update: Old Status: Active, old Category: Food')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (18, 7, N'admin', CAST(N'2020-02-27T14:31:58.000' AS DateTime), N'Update detail: Old DTO: Fried Chicken, food/7.jpg, It''s "crunchy" and "juicy", as well as "crispy". In addition, the dish has also been called "spicy" and "salty". , 8.0, 28, Food, Active')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (19, 2, N'admin', CAST(N'2020-02-27T14:32:20.000' AS DateTime), N'Update detail: Old DTO: Pizza, food/2.jpg,  It taste chewy and crisp, with marinara sauce and cheese., 11.0, 79, Food, Active')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (20, 19, N'admin', CAST(N'2020-02-27T15:50:13.000' AS DateTime), N'Update detail: Old DTO: Fish''s meet, food/11.jpg, Fresh, 11.0, 20, Food, Inactive')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (21, 19, N'admin', CAST(N'2020-02-27T16:00:38.000' AS DateTime), N'Update detail: Old DTO: New Fish''s meet, food/13.jpg, Fresh, 11.0, 20, Food, Inactive')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (22, 3, N'admin', CAST(N'2020-02-27T16:08:06.000' AS DateTime), N'Quick Update: Old Status: Active, old Category: Food')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (23, 6, N'admin', CAST(N'2020-02-27T16:08:18.000' AS DateTime), N'Quick Update: Old Status: Active, old Category: Food')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (24, 6, N'admin', CAST(N'2020-02-27T16:08:46.000' AS DateTime), N'Quick Update: Old Status: Active, old Category: Drink')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (25, 19, N'admin', CAST(N'2020-02-27T16:08:54.000' AS DateTime), N'Quick Update: Old Status: Inactive, old Category: Food')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (26, 6, N'admin', CAST(N'2020-02-27T16:08:59.000' AS DateTime), N'Quick Update: Old Status: Inactive, old Category: Drink')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (27, 3, N'admin', CAST(N'2020-02-27T16:10:09.000' AS DateTime), N'Update detail: Old DTO: Apple, food/3.jpg, Fruit that serves as filling for a pie around Than, 4.0, 47, Food, Inactive')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (28, 18, N'admin', CAST(N'2020-02-27T16:21:17.000' AS DateTime), N'Update detail: Old DTO: Food3, food/10.jpg, Fully, 10.0, 30, Food, Active')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (29, 18, N'admin', CAST(N'2020-02-27T16:37:23.000' AS DateTime), N'Update detail: Old DTO: Monster, food/10.jpg, Fowerful drink., 5.0, 30, Drink, Active')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (30, 18, N'admin', CAST(N'2020-02-27T16:37:58.000' AS DateTime), N'Update detail: Old DTO: Food3, food/10.jpg, Fowerful drink., 5.0, 30, Food, Active')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (31, 18, N'admin', CAST(N'2020-02-27T16:40:39.000' AS DateTime), N'Update detail: Old DTO: Monster, food/10.jpg, Fowerful drink., 5.0, 30, Drink, Active')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (32, 18, N'admin', CAST(N'2020-02-27T20:52:43.000' AS DateTime), N'Quick Update: Old Status: Active, old Category: Food')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (33, 18, N'admin', CAST(N'2020-02-27T20:52:49.000' AS DateTime), N'Quick Update: Old Status: Active, old Category: Drink')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (34, 18, N'admin', CAST(N'2020-02-27T20:52:56.000' AS DateTime), N'Quick Update: Old Status: Inactive, old Category: Drink')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (35, 20, N'admin', CAST(N'2020-02-27T22:23:14.000' AS DateTime), N'Quick Update: Old Status: Active, old Category: Food')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (36, 20, N'admin', CAST(N'2020-02-27T22:56:46.000' AS DateTime), N'Update detail: Old DTO: New Food 1, food/12.jpg, Ngon ngon, 12.0, 34, Food, Inactive')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (37, 20, N'admin', CAST(N'2020-02-27T22:57:14.000' AS DateTime), N'Update detail: Old DTO: New Food 1, food/12.jpg, Ngon ngon, 11.0, 34, Food, Active')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (38, 20, N'admin', CAST(N'2020-02-27T22:57:34.000' AS DateTime), N'Update detail: Old DTO: New Food 1, food/12.jpg, Ngon ngon, 11.0, 34, Food, Active')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (39, 20, N'admin', CAST(N'2020-02-27T22:57:43.000' AS DateTime), N'Quick Update: Old Status: Inactive, old Category: Food')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (40, 20, N'admin', CAST(N'2020-02-27T22:57:50.000' AS DateTime), N'Quick Update: Old Status: Active, old Category: Food')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (41, 1, N'admin', CAST(N'2020-02-27T22:58:31.000' AS DateTime), N'Update detail: Old DTO: Hamburger, food/1.jpg, Hamburgers are traditionally made with ground beef and served with onions, tomatoes, lettuce, ketchup, and other garnishes., 5.8, 90, Food, Active')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (42, 19, N'admin', CAST(N'2020-02-28T23:49:40.000' AS DateTime), N'Quick Update: Old Status: Active, old Category: Food')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (43, 20, N'admin', CAST(N'2020-02-28T23:49:49.000' AS DateTime), N'Quick Update: Old Status: Active, old Category: Fast Food')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (44, 20, N'admin', CAST(N'2020-02-28T23:49:55.000' AS DateTime), N'Quick Update: Old Status: Inactive, old Category: Fast Food')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (45, 4, N'admin', CAST(N'2020-03-01T10:04:34.000' AS DateTime), N'Quick Update: Old Status: Active, old Category: Food')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (46, 22, N'admin', CAST(N'2020-03-01T10:17:09.000' AS DateTime), N'Update detail: Old DTO: Lemon Sea, food/13.jpg, Cool, fresh, 4.0, 10, Drink, Active')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (47, 22, N'admin', CAST(N'2020-03-01T10:17:34.000' AS DateTime), N'Update detail: Old DTO: Lemon Sea, food/13.jpg, Cool, fresh, 4.0, 0, Drink, Inactive')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (48, 32, N'admin', CAST(N'2020-03-01T10:32:17.000' AS DateTime), N'Quick Update: Old Status: Active, old Category: Food')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (49, 32, N'admin', CAST(N'2020-03-01T10:32:24.000' AS DateTime), N'Quick Update: Old Status: Active, old Category: Food')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (50, 31, N'admin', CAST(N'2020-03-01T10:32:29.000' AS DateTime), N'Quick Update: Old Status: Active, old Category: Drink')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (51, 4, N'admin', CAST(N'2020-03-01T10:32:40.000' AS DateTime), N'Quick Update: Old Status: Active, old Category: Fast Food')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (52, 32, N'admin', CAST(N'2020-03-01T10:34:37.000' AS DateTime), N'Quick Update: Old Status: Active, old Category: Food')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (53, 30, N'admin', CAST(N'2020-03-01T10:37:54.000' AS DateTime), N'Update detail: Old DTO: Orange Fruit, food/21.jpg, Provice vitamin C, 5.0, 0, Drink, Inactive')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (54, 31, N'admin', CAST(N'2020-03-01T10:37:59.000' AS DateTime), N'Quick Update: Old Status: Active, old Category: Drink')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (55, 31, N'admin', CAST(N'2020-03-01T10:38:03.000' AS DateTime), N'Quick Update: Old Status: Inactive, old Category: Drink')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (56, 31, N'admin', CAST(N'2020-03-01T10:38:05.000' AS DateTime), N'Quick Update: Old Status: Active, old Category: Drink')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (57, 31, N'admin', CAST(N'2020-03-01T10:38:08.000' AS DateTime), N'Quick Update: Old Status: Active, old Category: Fast Food')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (58, 25, N'admin', CAST(N'2020-03-01T10:38:24.000' AS DateTime), N'Quick Update: Old Status: Active, old Category: Food')
INSERT [dbo].[HistoryUpdate] ([HistoryID], [FoodID], [UserID], [UpdateDate], [Detail]) VALUES (59, 3, N'admin', CAST(N'2020-03-01T10:40:33.000' AS DateTime), N'Update detail: Old DTO: Coca cola, food/3.jpg, Drink has gas, cool, 6.0, 47, Drink, Active')
SET IDENTITY_INSERT [dbo].[HistoryUpdate] OFF
SET IDENTITY_INSERT [dbo].[ItemsInCart] ON 

INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (5, 1, 5.8000001907348633, 7, 40.600001335144043, 1)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (6, 2, 11, 12, 132, 1)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (7, 1, 5.8000001907348633, 1, 5.8000001907348633, 14)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (8, 2, 11, 1, 11, 14)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (9, 2, 11, 2, 22, 24)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (14, 2, 11, 5, 55, 25)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (15, 1, 5.8000001907348633, 4, 23.200000762939453, 30)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (16, 2, 11, 2, 22, 30)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (20, 2, 11, 3, 33, 34)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (21, 1, 5.8000001907348633, 11, 63.800002098083496, 35)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (22, 2, 11, 80, 880, 35)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (45, 1, 5.8000001907348633, 10, 58.000001907348633, 40)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (46, 1, 5.8000001907348633, 1, 5.8000001907348633, 41)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (47, 2, 11, 1, 11, 41)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (48, 1, 5.8000001907348633, 80, 464.00001525878906, 42)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (49, 7, 8, 2, 16, 43)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (50, 3, 4, 3, 12, 43)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (51, 7, 8, 20, 160, 36)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (52, 16, 15, 1, 15, 44)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (53, 1, 4.8000001907348633, 2, 9.6000003814697266, 44)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (54, 20, 12, 1, 12, 36)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (55, 22, 4, 1, 4, 46)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (56, 19, 11, 1, 11, 46)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (57, 17, 14, 1, 14, 46)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (58, 4, 11, 1, 11, 46)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (59, 1, 5.8000001907348633, 1, 5.8000001907348633, 46)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (60, 19, 11, 3, 33, 47)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (61, 18, 6, 1, 6, 47)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (62, 2, 11, 1, 11, 47)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (63, 22, 4, 1, 4, 48)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (64, 18, 6, 1, 6, 48)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (65, 22, 4, 1, 4, 44)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (66, 20, 11, 2, 22, 49)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (67, 19, 11, 1, 11, 50)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (68, 18, 6, 1, 6, 50)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (70, 20, 11, 1, 11, 51)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (71, 22, 4, 1, 4, 45)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (72, 19, 11, 1, 11, 45)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (73, 18, 6, 1, 6, 45)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (74, 22, 4, 1, 4, 52)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (75, 19, 11, 1, 11, 52)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (76, 20, 11, 1, 11, 53)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (77, 20, 11, 1, 11, 47)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (78, 20, 11, 1, 11, 55)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (79, 22, 4, 1, 4, 56)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (80, 22, 4, 1, 4, 51)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (81, 19, 11, 1, 11, 51)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (82, 22, 4, 1, 4, 59)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (83, 19, 11, 1, 11, 60)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (84, 17, 14, 1, 14, 60)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (85, 20, 11, 1, 11, 61)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (86, 18, 6, 1, 6, 62)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (87, 20, 11, 100, 1100, 63)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (88, 31, 8, 1, 8, 64)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (89, 25, 2, 2, 4, 64)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (90, 26, 10, 1, 10, 64)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (91, 25, 2, 2, 4, 65)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (92, 30, 5, 100, 500, 66)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (93, 30, 5, 1, 5, 54)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (94, 31, 8, 1, 8, 67)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (95, 30, 5, 1, 5, 68)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (96, 26, 10, 1, 10, 69)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (97, 30, 5, 1, 5, 70)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (98, 28, 6, 1, 6, 71)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (99, 20, 11, 1, 11, 72)
INSERT [dbo].[ItemsInCart] ([ItemID], [FoodID], [Price], [Quantity], [TotalPrice], [CartID]) VALUES (100, 28, 6, 2, 12, 73)
SET IDENTITY_INSERT [dbo].[ItemsInCart] OFF
ALTER TABLE [dbo].[Cart]  WITH CHECK ADD  CONSTRAINT [FK_Cart_Account] FOREIGN KEY([UserID])
REFERENCES [dbo].[Account] ([UserID])
GO
ALTER TABLE [dbo].[Cart] CHECK CONSTRAINT [FK_Cart_Account]
GO
ALTER TABLE [dbo].[FoodAndDrink]  WITH CHECK ADD  CONSTRAINT [FK_FoodAndDrink_CategoryItem] FOREIGN KEY([Category])
REFERENCES [dbo].[CategoryItem] ([Category])
GO
ALTER TABLE [dbo].[FoodAndDrink] CHECK CONSTRAINT [FK_FoodAndDrink_CategoryItem]
GO
ALTER TABLE [dbo].[HistoryUpdate]  WITH CHECK ADD  CONSTRAINT [FK_UpdateHistory_Account] FOREIGN KEY([UserID])
REFERENCES [dbo].[Account] ([UserID])
GO
ALTER TABLE [dbo].[HistoryUpdate] CHECK CONSTRAINT [FK_UpdateHistory_Account]
GO
ALTER TABLE [dbo].[HistoryUpdate]  WITH CHECK ADD  CONSTRAINT [FK_UpdateHistory_FoodAndDrink] FOREIGN KEY([FoodID])
REFERENCES [dbo].[FoodAndDrink] ([FoodID])
GO
ALTER TABLE [dbo].[HistoryUpdate] CHECK CONSTRAINT [FK_UpdateHistory_FoodAndDrink]
GO
ALTER TABLE [dbo].[ItemsInCart]  WITH CHECK ADD  CONSTRAINT [FK_ItemsInCart_Cart] FOREIGN KEY([CartID])
REFERENCES [dbo].[Cart] ([CartID])
GO
ALTER TABLE [dbo].[ItemsInCart] CHECK CONSTRAINT [FK_ItemsInCart_Cart]
GO
ALTER TABLE [dbo].[ItemsInCart]  WITH CHECK ADD  CONSTRAINT [FK_ItemsInCart_FoodAndDrink] FOREIGN KEY([FoodID])
REFERENCES [dbo].[FoodAndDrink] ([FoodID])
GO
ALTER TABLE [dbo].[ItemsInCart] CHECK CONSTRAINT [FK_ItemsInCart_FoodAndDrink]
GO
/****** Object:  Trigger [dbo].[trg_SetStatusFood]    Script Date: 03/01/2020 11:23:50 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[trg_SetStatusFood] ON [dbo].[FoodAndDrink] AFTER UPDATE AS
BEGIN
	UPDATE dbo.FoodAndDrink SET Status='Inactive' 
	WHERE Quantity = 0
	;
	
END
GO
ALTER TABLE [dbo].[FoodAndDrink] ENABLE TRIGGER [trg_SetStatusFood]
GO
/****** Object:  Trigger [dbo].[trg_TotalInCart]    Script Date: 03/01/2020 11:23:50 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[trg_TotalInCart] ON [dbo].[ItemsInCart] AFTER UPDATE, INSERT AS
BEGIN
	UPDATE dbo.Cart SET Total 
	= (SELECT SUM(TotalPrice) 
		FROM dbo.ItemsInCart
		WHERE CartID = dbo.Cart.CartID
		)

	;
END
GO
ALTER TABLE [dbo].[ItemsInCart] ENABLE TRIGGER [trg_TotalInCart]
GO
/****** Object:  Trigger [dbo].[trg_TotalPriceInAItem]    Script Date: 03/01/2020 11:23:50 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[trg_TotalPriceInAItem] ON [dbo].[ItemsInCart] AFTER UPDATE AS
BEGIN
	UPDATE dbo.ItemsInCart SET TotalPrice = Quantity*Price

	FROM dbo.ItemsInCart
	;
END
------------------
GO
ALTER TABLE [dbo].[ItemsInCart] ENABLE TRIGGER [trg_TotalPriceInAItem]
GO
USE [master]
GO
ALTER DATABASE [HanaShop] SET  READ_WRITE 
GO
