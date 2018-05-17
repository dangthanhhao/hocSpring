USE [master]
GO
/****** Object:  Database [MusicStore]    Script Date: 5/18/2018 1:04:48 AM ******/
CREATE DATABASE [MusicStore]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'murach', FILENAME = N'E:\SQLSever\MSSQL12.HAOSV\MSSQL\DATA\murach.mdf' , SIZE = 3264KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'murach_log', FILENAME = N'E:\SQLSever\MSSQL12.HAOSV\MSSQL\DATA\murach_log.ldf' , SIZE = 832KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [MusicStore] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [MusicStore].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [MusicStore] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [MusicStore] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [MusicStore] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [MusicStore] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [MusicStore] SET ARITHABORT OFF 
GO
ALTER DATABASE [MusicStore] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [MusicStore] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [MusicStore] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [MusicStore] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [MusicStore] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [MusicStore] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [MusicStore] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [MusicStore] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [MusicStore] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [MusicStore] SET  ENABLE_BROKER 
GO
ALTER DATABASE [MusicStore] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [MusicStore] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [MusicStore] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [MusicStore] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [MusicStore] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [MusicStore] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [MusicStore] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [MusicStore] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [MusicStore] SET  MULTI_USER 
GO
ALTER DATABASE [MusicStore] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [MusicStore] SET DB_CHAINING OFF 
GO
ALTER DATABASE [MusicStore] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [MusicStore] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [MusicStore] SET DELAYED_DURABILITY = DISABLED 
GO
USE [MusicStore]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 5/18/2018 1:04:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Account](
	[UsersName] [varchar](50) NOT NULL,
	[Password] [varchar](50) NOT NULL,
	[Permission] [int] NOT NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[UsersName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Download]    Script Date: 5/18/2018 1:04:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Download](
	[DownloadID] [int] NOT NULL,
	[UserID] [int] NOT NULL,
	[DownloadDate] [datetime] NOT NULL,
	[ProductCode] [varchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[DownloadID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Invoice]    Script Date: 5/18/2018 1:04:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Invoice](
	[InvoiceID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [int] NOT NULL,
	[InvoiceDate] [datetime] NOT NULL,
	[TotalAmount] [float] NOT NULL,
	[IsProcessed] [int] NULL,
 CONSTRAINT [PK__Invoice__D796AAD520883BFA] PRIMARY KEY CLUSTERED 
(
	[InvoiceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[LineItem]    Script Date: 5/18/2018 1:04:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LineItem](
	[LineItemID] [int] IDENTITY(1,1) NOT NULL,
	[InvoiceID] [int] NOT NULL,
	[ProductID] [int] NOT NULL,
	[Quantity] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[LineItemID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Product]    Script Date: 5/18/2018 1:04:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Product](
	[ProductID] [int] IDENTITY(1,1) NOT NULL,
	[ProductCode] [varchar](10) NOT NULL,
	[ProductDescription] [nvarchar](100) NOT NULL,
	[ProductPrice] [decimal](7, 2) NOT NULL,
	[ProductURL] [nvarchar](max) NULL,
	[ImageURL] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[ProductID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[SongExample]    Script Date: 5/18/2018 1:04:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[SongExample](
	[ProductID] [int] NOT NULL,
	[ExampleSongName] [nvarchar](50) NOT NULL,
	[ExampleSongURL] [nvarchar](max) NULL,
	[IsExample] [binary](1) NULL,
 CONSTRAINT [PK_SongExample] PRIMARY KEY CLUSTERED 
(
	[ProductID] ASC,
	[ExampleSongName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Users]    Script Date: 5/18/2018 1:04:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Users](
	[UserID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NULL,
	[Email] [varchar](50) NULL,
	[CompanyName] [nvarchar](50) NULL,
	[Address] [nvarchar](max) NULL,
	[City] [nvarchar](50) NULL,
	[State] [nvarchar](50) NULL,
	[Zip] [varchar](50) NULL,
	[Country] [nvarchar](50) NULL,
	[CreditCardType] [nvarchar](50) NULL,
	[CreditCardNumber] [varchar](50) NULL,
	[CreditCardExpirationDate] [varchar](50) NULL,
	[UserAccount] [varchar](50) NULL,
	[Subcribes] [int] NULL,
 CONSTRAINT [PK__Users__1788CCAC4C419BBB] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([UserID], [Name], [Email], [CompanyName], [Address], [City], [State], [Zip], [Country], [CreditCardType], [CreditCardNumber], [CreditCardExpirationDate], [UserAccount], [Subcribes]) VALUES (1, N'Đặng Thanh', N'aszqsc@gmail.com', N'Đại học SP', N'Liên Chiểu, Đà Nẵng', N'Đà Nẵng', N'Miền Trung', N'0123', N'Việt Nam', N'VISA', N'123654', N'2020/1/1', NULL, NULL)
SET IDENTITY_INSERT [dbo].[Users] OFF
SET ANSI_PADDING ON

GO
/****** Object:  Index [IX_Product]    Script Date: 5/18/2018 1:04:48 AM ******/
CREATE UNIQUE NONCLUSTERED INDEX [IX_Product] ON [dbo].[Product]
(
	[ProductCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Invoice] ADD  CONSTRAINT [DF__Invoice__Invoice__2C3393D0]  DEFAULT (getdate()) FOR [InvoiceDate]
GO
ALTER TABLE [dbo].[Invoice] ADD  CONSTRAINT [DF__Invoice__TotalAm__2D27B809]  DEFAULT ((0)) FOR [TotalAmount]
GO
ALTER TABLE [dbo].[LineItem] ADD  DEFAULT ('1') FOR [InvoiceID]
GO
ALTER TABLE [dbo].[LineItem] ADD  DEFAULT ('1') FOR [ProductID]
GO
ALTER TABLE [dbo].[LineItem] ADD  DEFAULT ('1') FOR [Quantity]
GO
ALTER TABLE [dbo].[Product] ADD  DEFAULT ('') FOR [ProductCode]
GO
ALTER TABLE [dbo].[Product] ADD  DEFAULT ('') FOR [ProductDescription]
GO
ALTER TABLE [dbo].[Product] ADD  DEFAULT ('0.00') FOR [ProductPrice]
GO
ALTER TABLE [dbo].[Download]  WITH CHECK ADD  CONSTRAINT [FK__Download__UserID__3A81B327] FOREIGN KEY([UserID])
REFERENCES [dbo].[Users] ([UserID])
GO
ALTER TABLE [dbo].[Download] CHECK CONSTRAINT [FK__Download__UserID__3A81B327]
GO
ALTER TABLE [dbo].[Download]  WITH CHECK ADD  CONSTRAINT [FK_Download_Product] FOREIGN KEY([ProductCode])
REFERENCES [dbo].[Product] ([ProductCode])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Download] CHECK CONSTRAINT [FK_Download_Product]
GO
ALTER TABLE [dbo].[Invoice]  WITH CHECK ADD  CONSTRAINT [FK__Invoice__UserID__2B3F6F97] FOREIGN KEY([UserID])
REFERENCES [dbo].[Users] ([UserID])
GO
ALTER TABLE [dbo].[Invoice] CHECK CONSTRAINT [FK__Invoice__UserID__2B3F6F97]
GO
ALTER TABLE [dbo].[LineItem]  WITH CHECK ADD  CONSTRAINT [FK__LineItem__Invoic__30F848ED] FOREIGN KEY([InvoiceID])
REFERENCES [dbo].[Invoice] ([InvoiceID])
GO
ALTER TABLE [dbo].[LineItem] CHECK CONSTRAINT [FK__LineItem__Invoic__30F848ED]
GO
ALTER TABLE [dbo].[LineItem]  WITH CHECK ADD  CONSTRAINT [FK_LineItem_Product] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product] ([ProductID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[LineItem] CHECK CONSTRAINT [FK_LineItem_Product]
GO
ALTER TABLE [dbo].[SongExample]  WITH CHECK ADD  CONSTRAINT [FK_SongExample_Product] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product] ([ProductID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[SongExample] CHECK CONSTRAINT [FK_SongExample_Product]
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [FK_Users_Account] FOREIGN KEY([UserAccount])
REFERENCES [dbo].[Account] ([UsersName])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [FK_Users_Account]
GO
USE [master]
GO
ALTER DATABASE [MusicStore] SET  READ_WRITE 
GO
