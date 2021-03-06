USE [master]
GO
/****** Object:  Database [edoc]    Script Date: 05/19/2015 15:39:38 ******/
CREATE DATABASE [edoc] ON  PRIMARY 
( NAME = N'edoc', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\edoc.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'edoc_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\edoc_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [edoc] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [edoc].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [edoc] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [edoc] SET ANSI_NULLS OFF
GO
ALTER DATABASE [edoc] SET ANSI_PADDING OFF
GO
ALTER DATABASE [edoc] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [edoc] SET ARITHABORT OFF
GO
ALTER DATABASE [edoc] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [edoc] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [edoc] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [edoc] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [edoc] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [edoc] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [edoc] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [edoc] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [edoc] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [edoc] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [edoc] SET  DISABLE_BROKER
GO
ALTER DATABASE [edoc] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [edoc] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [edoc] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [edoc] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [edoc] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [edoc] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [edoc] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [edoc] SET  READ_WRITE
GO
ALTER DATABASE [edoc] SET RECOVERY SIMPLE
GO
ALTER DATABASE [edoc] SET  MULTI_USER
GO
ALTER DATABASE [edoc] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [edoc] SET DB_CHAINING OFF
GO
USE [edoc]
GO
/****** Object:  User [amant.kumar]    Script Date: 05/19/2015 15:39:38 ******/
CREATE USER [amant.kumar] FOR LOGIN [IGGLOBAL\Amant.Kumar] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  Table [dbo].[flags]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[flags](
	[flag_id] [int] NOT NULL,
	[flag_name] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[flag_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PIM_COLLEGE_MASTER]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PIM_COLLEGE_MASTER](
	[COLLEGE_ID] [int] IDENTITY(1,1) NOT NULL,
	[COLLEGE_NAME] [varchar](200) NULL,
	[COLLEGE_STATUS] [char](1) NULL,
	[COLLEGE_MODIFIEDBY] [varchar](100) NULL,
	[COLLEGE_TIMESTAMP] [datetime] NULL,
	[oldcollegecode] [char](16) NULL,
 CONSTRAINT [PK_PIM_COLLEGE_MASTER] PRIMARY KEY CLUSTERED 
(
	[COLLEGE_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [IX_PIM_COLLEGE_MASTER] UNIQUE NONCLUSTERED 
(
	[COLLEGE_NAME] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PIM_DISCIPLINE_MASTER]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PIM_DISCIPLINE_MASTER](
	[DISCIPLINE_ID] [int] IDENTITY(1,1) NOT NULL,
	[DISCIPLINE_NAME] [varchar](100) NOT NULL,
	[DISCIPLINE_STATUS] [char](1) NOT NULL,
	[DISCIPLINE_MODIFIEDBY] [varchar](100) NOT NULL,
	[DISCIPLINE_TIMESTAMP] [datetime] NOT NULL,
 CONSTRAINT [PK_PIM_DISCIPLINE_MASTER] PRIMARY KEY CLUSTERED 
(
	[DISCIPLINE_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [IX_PIM_DISCIPLINE_MASTER] UNIQUE NONCLUSTERED 
(
	[DISCIPLINE_NAME] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PIM_DEGREE_MASTER]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PIM_DEGREE_MASTER](
	[DEGREE_ID] [int] IDENTITY(1,1) NOT NULL,
	[DEGREE_NAME] [varchar](100) NULL,
	[DEGREE_STATUS] [char](1) NULL,
	[DEGREE_MODIFIEDBY] [varchar](100) NULL,
	[DEGREE_TIMESTAMP] [datetime] NULL,
	[olddegreecode] [char](16) NULL,
 CONSTRAINT [PK_PIM_DEGREE_MASTER] PRIMARY KEY CLUSTERED 
(
	[DEGREE_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [IX_PIM_DEGREE_MASTER] UNIQUE NONCLUSTERED 
(
	[DEGREE_NAME] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PIM_LEVEL_MASTER]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PIM_LEVEL_MASTER](
	[LEVEL_ID] [int] IDENTITY(1,1) NOT NULL,
	[LEVEL_NAME] [varchar](100) NOT NULL,
	[LEVEL_STATUS] [char](1) NOT NULL,
	[LEVEL_MODIFIEDBY] [varchar](100) NOT NULL,
	[LEVEL_TIMESTAMP] [datetime] NOT NULL,
	[oldlevelcode] [char](16) NULL,
 CONSTRAINT [PK_PIM_LEVEL_MASTER] PRIMARY KEY CLUSTERED 
(
	[LEVEL_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [IX_PIM_LEVEL_MASTER] UNIQUE NONCLUSTERED 
(
	[LEVEL_NAME] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[users]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[users](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [id] UNIQUE NONCLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[document_type]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[document_type](
	[document_type_id] [int] NOT NULL,
	[type_name] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[document_type_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[document_class]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[document_class](
	[class_id] [int] NOT NULL,
	[class_name] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[class_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[default_folders]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[default_folders](
	[name] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[emp_other_detail]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[emp_other_detail](
	[application_id] [int] NULL,
	[if_referred] [varchar](10) NULL,
	[if_contract] [varchar](10) NULL,
	[if_Objection] [varchar](10) NULL,
	[if_Applied_Before] [varchar](10) NULL,
	[if_Applied_For_Visa] [varchar](10) NULL,
	[location_Preference] [varchar](10) NULL,
	[strength] [varchar](50) NULL,
	[to_Improve] [varchar](50) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[background_check_personal_detail]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[background_check_personal_detail](
	[application_id] [varchar](20) NULL,
	[name_Of_Applicant] [varchar](50) NULL,
	[date_Of_Birth] [varchar](50) NULL,
	[place_Of_Birth] [varchar](50) NULL,
	[sex] [varchar](50) NULL,
	[nationality] [varchar](50) NULL,
	[father_Name] [varchar](50) NULL,
	[passport_Number] [varchar](50) NULL,
	[home_Phone] [varchar](50) NULL,
	[office_Phone] [varchar](50) NULL,
	[mobile] [varchar](50) NOT NULL,
	[permanent_Address] [varchar](200) NULL,
	[permanent_City] [varchar](50) NULL,
	[permanent_State] [varchar](50) NULL,
	[permanent_Pin_Code] [varchar](50) NULL,
	[permanent_Telephone] [varchar](50) NULL,
	[permanent_Duration_From] [varchar](50) NULL,
	[permanent_Duration_To] [varchar](50) NULL,
	[permanent_Nature_Of_Location] [varchar](50) NULL,
	[present_Address] [varchar](200) NULL,
	[present_City] [varchar](50) NULL,
	[present_State] [varchar](50) NULL,
	[present_Pin_Code] [varchar](50) NULL,
	[present_Telephone] [varchar](50) NULL,
	[present_Duration_From] [varchar](50) NULL,
	[present_Duration_To] [varchar](50) NULL,
	[present_Nature_Of_Location] [varchar](50) NULL,
	[i_Agree] [varchar](50) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[background_check_employment_detail]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[background_check_employment_detail](
	[application_Id] [varchar](20) NULL,
	[employer_Name] [varchar](50) NULL,
	[employee_Id] [varchar](50) NULL,
	[employment_From] [varchar](50) NULL,
	[employment_To] [varchar](50) NULL,
	[street_Address] [varchar](50) NULL,
	[employer_Telephone] [varchar](50) NULL,
	[remuneration] [varchar](50) NULL,
	[employer_City] [varchar](50) NULL,
	[employer_State] [varchar](50) NULL,
	[employer_Country] [varchar](50) NULL,
	[employer_Postal_Code] [varchar](50) NULL,
	[designation_Held] [varchar](50) NULL,
	[reason_For_Leaving] [varchar](50) NULL,
	[employment_Status] [varchar](50) NULL,
	[outsourcing_Agency_Name] [varchar](50) NULL,
	[outsourcing_Agency_Address] [varchar](50) NULL,
	[outsourcing_Agency_Telephone] [varchar](50) NULL,
	[supervisor_Name] [varchar](50) NULL,
	[supervisor_Title] [varchar](50) NULL,
	[supervisor_Telephone] [varchar](50) NULL,
	[supervisor_Email] [varchar](50) NULL,
	[hr_Manager_Name] [varchar](50) NULL,
	[hr_Manager_Telephone] [varchar](50) NULL,
	[hr_Manager_Email] [varchar](50) NULL,
	[employment_Description] [varchar](50) NULL,
	[current_Employment_Authority] [varchar](50) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[background_check_educational_detail]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[background_check_educational_detail](
	[application_Id] [varchar](20) NULL,
	[examination_Passed] [varchar](50) NULL,
	[institution] [varchar](50) NULL,
	[board] [varchar](50) NULL,
	[course_Attended] [varchar](50) NULL,
	[marks] [varchar](50) NULL,
	[from_To_Date] [varchar](50) NULL,
	[roll_Number] [varchar](50) NULL,
	[discipline] [varchar](50) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[emp_salary_detail]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[emp_salary_detail](
	[application_id] [int] NULL,
	[current_ctc_monthly] [varchar](10) NULL,
	[current_ctc_annual] [varchar](10) NULL,
	[current_salary_monthly] [varchar](10) NULL,
	[current_salary_annual] [varchar](10) NULL,
	[current_salary_fixed_monthly] [varchar](10) NULL,
	[current_salary_fixed_annual] [varchar](10) NULL,
	[current_salary_variable_monthly] [varchar](10) NULL,
	[current_salary_variable_annual] [varchar](10) NULL,
	[incentive_monthly] [varchar](10) NULL,
	[incentive_annual] [varchar](10) NULL,
	[notice_period] [varchar](10) NULL,
	[expected_salary] [varchar](10) NULL,
	[expected_joining_date] [varchar](10) NULL,
	[monthly_in_hand] [varchar](10) NULL,
	[achievements] [varchar](200) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  UserDefinedDataType [dbo].[StaffId]    Script Date: 05/19/2015 15:39:38 ******/
CREATE TYPE [dbo].[StaffId] FROM [char](20) NOT NULL
GO
/****** Object:  Table [dbo].[skills]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[skills](
	[skill_id] [int] NOT NULL,
	[skill_name] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[skill_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[roles]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[roles](
	[role_id] [int] NOT NULL,
	[role] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[role_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[pre_emp]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[pre_emp](
	[username] [varchar](50) NOT NULL,
	[application_id] [int] IDENTITY(1,1) NOT NULL,
	[password] [varchar](50) NULL,
	[first_name] [varchar](50) NULL,
	[last_name] [varchar](50) NULL,
	[email] [varchar](50) NULL,
	[contact] [varchar](50) NULL,
	[address] [varchar](50) NULL,
	[zip] [int] NULL,
	[file_location] [varchar](50) NULL,
	[pre_flag] [int] NOT NULL,
	[edit_flag] [int] NOT NULL,
	[enable_flag] [int] NOT NULL,
	[rmg_done_flag] [int] NOT NULL,
	[rmg_admin_done_flag] [int] NOT NULL,
	[total_edit_flag] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[application_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [user_id] UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PIM_UNIVERSITY_MASTER]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PIM_UNIVERSITY_MASTER](
	[UNV_ID] [int] IDENTITY(1,1) NOT NULL,
	[UNV_NAME] [varchar](100) NULL,
	[UNV_STATUS] [char](1) NULL,
	[UNV_MODIFIEDBY] [varchar](100) NULL,
	[UNV_TIMESTAMP] [datetime] NULL,
	[oldunivcode] [char](16) NULL,
 CONSTRAINT [PK_PIM_UNIVERSITY_MASTER] PRIMARY KEY CLUSTERED 
(
	[UNV_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [IX_PIM_UNIVERSITY_MASTER] UNIQUE NONCLUSTERED 
(
	[UNV_NAME] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PIM_SUBJECT_MASTER]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PIM_SUBJECT_MASTER](
	[SUBJECT_ID] [int] IDENTITY(1,1) NOT NULL,
	[SUBJECT_NAME] [varchar](100) NOT NULL,
	[SUBJECT_STATUS] [char](1) NOT NULL,
	[SUBJECT_MODIFIEDBY] [varchar](100) NOT NULL,
	[SUBJECT_TIMESTAMP] [datetime] NOT NULL,
	[oldsubjectcode] [char](16) NULL,
 CONSTRAINT [PK_PIM_SUBJECT_MASTER] PRIMARY KEY CLUSTERED 
(
	[SUBJECT_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [IX_PIM_SUBJECT_MASTER] UNIQUE NONCLUSTERED 
(
	[SUBJECT_NAME] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PIM_REFERENCES]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PIM_REFERENCES](
	[REFERENCE_ID] [int] IDENTITY(1,1) NOT NULL,
	[REFERENCE_EMP_ID] [dbo].[StaffId] NULL,
	[REFERENCE_PERSONNAME] [varchar](100) NULL,
	[REFERENCE_ORGANIZATION] [varchar](100) NULL,
	[REFERENCE_DATEREF] [datetime] NULL,
	[REFERENCE_PHONENO] [varchar](50) NULL,
	[REFERENCE_EMAILID] [varchar](100) NULL,
	[REFERENCE_COMMENTS] [varchar](500) NULL,
	[REFERENCE_MODIFIEDBY] [varchar](100) NULL,
	[REFERENCE_TIMESTAMP] [datetime] NULL,
	[REFERENCE_ACTIVE] [char](1) NULL,
 CONSTRAINT [PK_PIM_REFERENCES] PRIMARY KEY CLUSTERED 
(
	[REFERENCE_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PIM_PAST_EMPLOYMENT]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PIM_PAST_EMPLOYMENT](
	[PAST_EMP_ID] [int] IDENTITY(1,1) NOT NULL,
	[PAST_EMP_EMP_ID] [dbo].[StaffId] NOT NULL,
	[PAST_EMP_TYPE] [char](1) NOT NULL,
	[PAST_EMP_COMPANYNAME] [varchar](100) NOT NULL,
	[PAST_EMP_PROJECTNAME] [varchar](100) NOT NULL,
	[PAST_EMP_JOBTITTLE] [int] NOT NULL,
	[PAST_EMP_COUNTRY_ID] [int] NOT NULL,
	[PAST_EMP_DATEFROM] [datetime] NOT NULL,
	[PAST_EMP_DATETO] [datetime] NULL,
	[PAST_EMP_ONGOING] [char](1) NOT NULL,
	[PAST_EMP_CLIENTNAME] [varchar](100) NOT NULL,
	[PAST_EMP_SKILLID] [int] NOT NULL,
	[PAST_EMP_KEYEXP] [varchar](100) NULL,
	[PAST_EMP_INDUSTRY_ID] [int] NOT NULL,
	[PAST_EMP_FUNCTION_ID] [int] NOT NULL,
	[PAST_EMP_MODIFIEDBY] [varchar](100) NOT NULL,
	[PAST_EMP_TIMESTAMP] [datetime] NOT NULL,
	[PAST_EMP_DESCRIPTION] [varchar](500) NULL,
 CONSTRAINT [PK_PIM_PAST_EMPLOYMENT] PRIMARY KEY CLUSTERED 
(
	[PAST_EMP_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [IX_PIM_PAST_EMPLOYMENT] UNIQUE NONCLUSTERED 
(
	[PAST_EMP_EMP_ID] ASC,
	[PAST_EMP_COMPANYNAME] ASC,
	[PAST_EMP_DATEFROM] ASC,
	[PAST_EMP_DATETO] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[file_category]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[file_category](
	[category_id] [int] IDENTITY(1,1) NOT NULL,
	[category_name] [varchar](50) NOT NULL,
	[parent_category_id] [int] NULL,
	[application_id] [int] NOT NULL,
	[visibility] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[category_id] ASC,
	[application_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [category_id] UNIQUE NONCLUSTERED 
(
	[category_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[emp_training_attended]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[emp_training_attended](
	[application_id] [int] NULL,
	[course_name] [varchar](20) NULL,
	[from_to_date] [varchar](50) NULL,
	[institution] [varchar](100) NULL,
	[certificate_awarded] [varchar](20) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[emp_skills]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[emp_skills](
	[application_id] [int] NULL,
	[skill_id] [int] NULL,
	[rating] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[emp_reference]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[emp_reference](
	[application_id] [int] NULL,
	[reference_name] [varchar](20) NULL,
	[organisation_Name] [varchar](20) NULL,
	[designation] [varchar](20) NULL,
	[contact] [varchar](20) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[emp_personal_detail]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[emp_personal_detail](
	[application_id] [int] NULL,
	[employee_id] [int] NULL,
	[name] [varchar](20) NULL,
	[date] [date] NULL,
	[date_of_birth] [date] NULL,
	[marital_status] [varchar](20) NULL,
	[permanent_address] [varchar](200) NULL,
	[permanent_telephone] [varchar](20) NULL,
	[present_address] [varchar](200) NULL,
	[present_address_telephone] [varchar](20) NULL,
	[present_address_mobile] [varchar](20) NULL,
	[passport] [varchar](20) NULL,
	[passport_Expiry] [varchar](20) NULL,
	[relevant_experience] [varchar](200) NULL,
	[visa] [varchar](20) NULL,
	[visa_Expiry] [varchar](20) NULL,
	[onsite_Experience] [varchar](200) NULL,
	[it_experience] [varchar](200) NULL,
	[primary_skill] [varchar](20) NULL,
	[secondary_skill] [varchar](20) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[emp_family_detail]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[emp_family_detail](
	[application_Id] [int] NULL,
	[member_name] [varchar](50) NULL,
	[occupation] [varchar](20) NULL,
	[age] [int] NULL,
	[dependent] [varchar](10) NULL,
	[present_location] [varchar](20) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[emp_employment_detail]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[emp_employment_detail](
	[application_id] [int] NULL,
	[employer_name] [varchar](50) NULL,
	[location] [varchar](50) NULL,
	[number_of_employee] [varchar](50) NULL,
	[months] [varchar](20) NULL,
	[years] [varchar](20) NULL,
	[experience] [varchar](20) NULL,
	[designation_held] [varchar](20) NULL,
	[reporting_to] [varchar](50) NULL,
	[reason_for_leaving] [varchar](50) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[emp_education_background]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[emp_education_background](
	[application_id] [int] NULL,
	[examination_passed] [varchar](50) NULL,
	[from_to_date] [varchar](50) NULL,
	[institution] [varchar](50) NULL,
	[board] [varchar](50) NULL,
	[marks] [varchar](10) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[emp_current_employment_detail]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[emp_current_employment_detail](
	[application_id] [int] NULL,
	[current_project_name] [varchar](50) NULL,
	[team_size] [varchar](20) NULL,
	[skills_used] [varchar](100) NULL,
	[roles_and_responsibilities] [varchar](50) NULL,
	[organisation_description] [varchar](200) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[emp]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[emp](
	[application_id] [int] NULL,
	[emp_id] [varchar](50) NULL,
	[first_name] [varchar](50) NULL,
	[last_name] [varchar](50) NULL,
	[email] [varchar](50) NULL,
	[contact] [varchar](50) NULL,
	[address] [varchar](50) NULL,
	[zip] [int] NULL,
	[file_location] [varchar](50) NULL,
	[emp_username] [varchar](50) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[default_files_and_folders]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[default_files_and_folders](
	[file_description_id] [int] NOT NULL,
	[format] [varchar](50) NOT NULL,
	[class_id] [int] NOT NULL,
	[document_type_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[file_description_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[user_role]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[user_role](
	[username] [varchar](50) NOT NULL,
	[role_id] [int] NOT NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PIM_FAMILY_DETAILS]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PIM_FAMILY_DETAILS](
	[FAMILY_ID] [int] IDENTITY(1,1) NOT NULL,
	[FAMILY_EMP_ID] [dbo].[StaffId] NOT NULL,
	[FAMILY_RELATION] [int] NULL,
	[FAMILY_NAME] [varchar](100) NULL,
	[FAMILY_DATEOFBIRTH] [datetime] NULL,
	[FAMILY_GENDER] [char](1) NULL,
	[FAMILY_DEPENDENT] [char](1) NULL,
	[FAMILY_ADDRESS] [varchar](100) NULL,
	[FAMILY_MINOR] [char](1) NULL,
	[FAMILY_GUARDIANNAME] [varchar](100) NULL,
	[FAMILY_GUARDIANADD] [varchar](100) NULL,
	[FAMILY_COMMENTS] [varchar](100) NULL,
	[FAMILY_MODIFIEDBY] [varchar](100) NULL,
	[FAMILY_TIMESTAMP] [datetime] NULL,
	[FAMILY_REL_CODE] [char](16) NULL,
 CONSTRAINT [PK_PIM_FAMILY_DETAILS] PRIMARY KEY CLUSTERED 
(
	[FAMILY_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PIM_EMPLOYEE_DETAILS]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PIM_EMPLOYEE_DETAILS](
	[EMP_DET_ID] [int] IDENTITY(1,1) NOT NULL,
	[EMP_DET_EMP_ID] [dbo].[StaffId] NULL,
	[EMP_DET_PLACEOFBIRTH] [varchar](50) NULL,
	[EMP_DET_MARITALSTATUS] [char](1) NULL,
	[SPOUSE_NAME] [char](50) NULL,
	[EMP_DET_WEDDINGDATE] [datetime] NULL,
	[EMP_DET_NATIONALITY] [int] NULL,
	[EMP_DET_PANNUMBER] [varchar](30) NULL,
	[EMP_DET_LANGUAGE_ID] [int] NULL,
	[EMP_DET_BLOODGROUP] [varchar](20) NULL,
	[EMP_DET_RH] [char](1) NULL,
	[EMP_DET_PERMANENTADD] [varchar](300) NULL,
	[EMP_DET_CURRENTADD] [varchar](300) NULL,
	[EMP_DET_UPLOAD_CV] [varchar](200) NULL,
	[EMP_DET_CV_LASTMODIFIEDON] [datetime] NULL,
	[EMP_DET_ISPERMANENTADD] [char](1) NULL,
	[EMP_DET_MODIFIEDBY] [varchar](100) NULL,
	[EMP_DET_TIMESTAMP] [datetime] NULL,
	[EMP_DET_ACTIVE] [char](1) NULL,
	[oldlang] [char](16) NULL,
	[oldnation] [char](16) NULL,
 CONSTRAINT [PK_EMPLOYEE_DETAILS] PRIMARY KEY CLUSTERED 
(
	[EMP_DET_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PIM_EDUCATIONAL_DETAILS]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PIM_EDUCATIONAL_DETAILS](
	[EDU_ID] [int] IDENTITY(1,1) NOT NULL,
	[EDU_EMP_ID] [dbo].[StaffId] NOT NULL,
	[EDU_TYPEOFESTABLISHMENT_ID] [int] NULL,
	[EDU_NAMEOFESTABLISHMENT_ID] [int] NULL,
	[EDU_AFFILIATEDTO_ID] [int] NULL,
	[EDU_ATTENDEDFROM] [datetime] NULL,
	[EDU_ATTENDEDTO] [datetime] NULL,
	[EDU_YEAROFPASSING] [int] NULL,
	[EDU_COMPANYSPONSORED] [char](1) NULL,
	[EDU_DEGREECERTIFICATESTATUS] [char](1) NULL,
	[EDU_ADDRESSOFINSTITUTE] [varchar](100) NULL,
	[EDU_DISCIPLINE_ID] [int] NULL,
	[EDU_LEVEL_ID] [int] NULL,
	[EDU_SUBJECT_ID] [int] NULL,
	[EDU_MINORFIELD_ID] [int] NULL,
	[EDU_MAJORFIELD_ID] [int] NULL,
	[EDU_MODIFIEDBY] [varchar](100) NULL,
	[EDU_TIMESTAMP] [datetime] NULL,
	[EDU_ACTIVE] [char](1) NULL,
	[EDU_GRADE_VALUE] [varchar](50) NULL,
	[EDU_GRADE_ID] [char](1) NULL,
	[oldegreecode] [char](16) NULL,
	[oldcollegecode] [char](16) NULL,
	[oldunivid] [char](16) NULL,
	[oldlevelcode] [char](16) NULL,
	[oldsubjectcode] [char](16) NULL,
	[oldmajorcode] [char](16) NULL,
	[oldminorcode] [char](16) NULL,
 CONSTRAINT [PK_PIM_EDUCATIONAL_DETAILS] PRIMARY KEY CLUSTERED 
(
	[EDU_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PIM_CONTACT_DETAILS]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PIM_CONTACT_DETAILS](
	[CONTACT_ID] [int] IDENTITY(1,1) NOT NULL,
	[CONTACT_EMP_ID] [dbo].[StaffId] NOT NULL,
	[CONTACT_HOMEPHONE] [varchar](50) NULL,
	[CONTACT_WORKMOBILENO] [varchar](50) NULL,
	[CONTACT_WORKPHONE] [varchar](50) NULL,
	[CONTACT_SEATNO] [varchar](20) NULL,
	[CONTACT_EXTNUMBER] [varchar](20) NULL,
	[CONTACT_WORKEMAIL] [varchar](100) NULL,
	[CONTACT_PERSONALMAIL] [varchar](100) NULL,
	[CONTACT_MODIFIEDBY] [varchar](100) NULL,
	[CONTACT_TIMESTAMP] [datetime] NULL,
	[CONTACT_ACTIVE] [char](1) NULL,
 CONSTRAINT [PK_PIM_CONTACT_DETAILS] PRIMARY KEY CLUSTERED 
(
	[CONTACT_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [IX_PIM_CONTACT_DETAILS] UNIQUE NONCLUSTERED 
(
	[CONTACT_EMP_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[hr_bgc]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[hr_bgc](
	[bgc_id] [int] IDENTITY(1,1) NOT NULL,
	[application_id] [int] NOT NULL,
	[bgc_done_flag] [int] NOT NULL,
	[remark] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[bgc_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[file_data]    Script Date: 05/19/2015 15:39:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[file_data](
	[file_id] [int] IDENTITY(1,1) NOT NULL,
	[application_id] [int] NOT NULL,
	[file_description_id] [int] NULL,
	[file_location] [varchar](500) NULL,
	[creation_date] [date] NULL,
	[last_modified_date] [date] NULL,
	[parent_category_id] [int] NOT NULL,
	[flag_id] [int] NOT NULL,
	[remark] [varchar](50) NULL,
	[fileName] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[file_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Default [DF__emp_other__appli__7B5B524B]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_other_detail] ADD  DEFAULT (NULL) FOR [application_id]
GO
/****** Object:  Default [DF__emp_other__if_re__7C4F7684]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_other_detail] ADD  DEFAULT (NULL) FOR [if_referred]
GO
/****** Object:  Default [DF__emp_other__if_co__7D439ABD]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_other_detail] ADD  DEFAULT (NULL) FOR [if_contract]
GO
/****** Object:  Default [DF__emp_other__if_Ob__7E37BEF6]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_other_detail] ADD  DEFAULT (NULL) FOR [if_Objection]
GO
/****** Object:  Default [DF__emp_other__if_Ap__7F2BE32F]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_other_detail] ADD  DEFAULT (NULL) FOR [if_Applied_Before]
GO
/****** Object:  Default [DF__emp_other__if_Ap__00200768]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_other_detail] ADD  DEFAULT (NULL) FOR [if_Applied_For_Visa]
GO
/****** Object:  Default [DF__emp_other__locat__01142BA1]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_other_detail] ADD  DEFAULT (NULL) FOR [location_Preference]
GO
/****** Object:  Default [DF__emp_other__stren__02084FDA]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_other_detail] ADD  DEFAULT (NULL) FOR [strength]
GO
/****** Object:  Default [DF__emp_other__to_Im__02FC7413]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_other_detail] ADD  DEFAULT (NULL) FOR [to_Improve]
GO
/****** Object:  Default [DF__emp_salar__appli__607251E5]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_salary_detail] ADD  DEFAULT (NULL) FOR [application_id]
GO
/****** Object:  Default [DF__emp_salar__curre__6166761E]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_salary_detail] ADD  DEFAULT (NULL) FOR [current_ctc_monthly]
GO
/****** Object:  Default [DF__emp_salar__curre__625A9A57]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_salary_detail] ADD  DEFAULT (NULL) FOR [current_ctc_annual]
GO
/****** Object:  Default [DF__emp_salar__curre__634EBE90]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_salary_detail] ADD  DEFAULT (NULL) FOR [current_salary_monthly]
GO
/****** Object:  Default [DF__emp_salar__curre__6442E2C9]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_salary_detail] ADD  DEFAULT (NULL) FOR [current_salary_annual]
GO
/****** Object:  Default [DF__emp_salar__curre__65370702]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_salary_detail] ADD  DEFAULT (NULL) FOR [current_salary_fixed_monthly]
GO
/****** Object:  Default [DF__emp_salar__curre__662B2B3B]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_salary_detail] ADD  DEFAULT (NULL) FOR [current_salary_fixed_annual]
GO
/****** Object:  Default [DF__emp_salar__curre__671F4F74]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_salary_detail] ADD  DEFAULT (NULL) FOR [current_salary_variable_monthly]
GO
/****** Object:  Default [DF__emp_salar__curre__681373AD]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_salary_detail] ADD  DEFAULT (NULL) FOR [current_salary_variable_annual]
GO
/****** Object:  Default [DF__emp_salar__incen__690797E6]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_salary_detail] ADD  DEFAULT (NULL) FOR [incentive_monthly]
GO
/****** Object:  Default [DF__emp_salar__incen__69FBBC1F]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_salary_detail] ADD  DEFAULT (NULL) FOR [incentive_annual]
GO
/****** Object:  Default [DF__emp_salar__notic__6AEFE058]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_salary_detail] ADD  DEFAULT (NULL) FOR [notice_period]
GO
/****** Object:  Default [DF__emp_salar__expec__6BE40491]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_salary_detail] ADD  DEFAULT (NULL) FOR [expected_salary]
GO
/****** Object:  Default [DF__emp_salar__expec__6CD828CA]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_salary_detail] ADD  DEFAULT (NULL) FOR [expected_joining_date]
GO
/****** Object:  Default [DF__emp_salar__month__6DCC4D03]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_salary_detail] ADD  DEFAULT (NULL) FOR [monthly_in_hand]
GO
/****** Object:  Default [DF__emp_salar__achie__6EC0713C]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_salary_detail] ADD  DEFAULT (NULL) FOR [achievements]
GO
/****** Object:  Default [DF__skills__skill_na__3F115E1A]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[skills] ADD  DEFAULT (NULL) FOR [skill_name]
GO
/****** Object:  Default [DF__pre_emp__edit_fl__3B40CD36]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[pre_emp] ADD  DEFAULT ('1') FOR [edit_flag]
GO
/****** Object:  Default [DF__pre_emp__enable___3C34F16F]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[pre_emp] ADD  DEFAULT ('1') FOR [enable_flag]
GO
/****** Object:  Default [DF__pre_emp__rmg_don__3D2915A8]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[pre_emp] ADD  DEFAULT ('0') FOR [rmg_done_flag]
GO
/****** Object:  Default [DF__pre_emp__rmg_adm__3E1D39E1]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[pre_emp] ADD  DEFAULT ('0') FOR [rmg_admin_done_flag]
GO
/****** Object:  Default [DF_pre_emp_total_edit_flag]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[pre_emp] ADD  CONSTRAINT [DF_pre_emp_total_edit_flag]  DEFAULT ((1)) FOR [total_edit_flag]
GO
/****** Object:  Default [DF__file_cate__paren__32AB8735]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[file_category] ADD  DEFAULT (NULL) FOR [parent_category_id]
GO
/****** Object:  Default [DF__file_cate__visib__339FAB6E]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[file_category] ADD  DEFAULT ('0') FOR [visibility]
GO
/****** Object:  Default [DF__emp_train__appli__2DE6D218]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_training_attended] ADD  DEFAULT (NULL) FOR [application_id]
GO
/****** Object:  Default [DF__emp_train__cours__2EDAF651]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_training_attended] ADD  DEFAULT (NULL) FOR [course_name]
GO
/****** Object:  Default [DF__emp_train__from___2FCF1A8A]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_training_attended] ADD  DEFAULT (NULL) FOR [from_to_date]
GO
/****** Object:  Default [DF__emp_train__insti__30C33EC3]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_training_attended] ADD  DEFAULT (NULL) FOR [institution]
GO
/****** Object:  Default [DF__emp_train__certi__31B762FC]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_training_attended] ADD  DEFAULT (NULL) FOR [certificate_awarded]
GO
/****** Object:  Default [DF__emp_skill__appli__2B0A656D]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_skills] ADD  DEFAULT (NULL) FOR [application_id]
GO
/****** Object:  Default [DF__emp_skill__skill__2BFE89A6]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_skills] ADD  DEFAULT (NULL) FOR [skill_id]
GO
/****** Object:  Default [DF__emp_skill__ratin__2CF2ADDF]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_skills] ADD  DEFAULT (NULL) FOR [rating]
GO
/****** Object:  Default [DF__emp_refer__appli__17036CC0]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_reference] ADD  DEFAULT (NULL) FOR [application_id]
GO
/****** Object:  Default [DF__emp_refer__refer__17F790F9]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_reference] ADD  DEFAULT (NULL) FOR [reference_name]
GO
/****** Object:  Default [DF__emp_refer__organ__18EBB532]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_reference] ADD  DEFAULT (NULL) FOR [organisation_Name]
GO
/****** Object:  Default [DF__emp_refer__desig__19DFD96B]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_reference] ADD  DEFAULT (NULL) FOR [designation]
GO
/****** Object:  Default [DF__emp_refer__conta__1AD3FDA4]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_reference] ADD  DEFAULT (NULL) FOR [contact]
GO
/****** Object:  Default [DF__emp_perso__appli__03F0984C]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_personal_detail] ADD  DEFAULT (NULL) FOR [application_id]
GO
/****** Object:  Default [DF__emp_perso__emplo__04E4BC85]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_personal_detail] ADD  DEFAULT (NULL) FOR [employee_id]
GO
/****** Object:  Default [DF__emp_person__name__05D8E0BE]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_personal_detail] ADD  DEFAULT (NULL) FOR [name]
GO
/****** Object:  Default [DF__emp_person__date__06CD04F7]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_personal_detail] ADD  DEFAULT (NULL) FOR [date]
GO
/****** Object:  Default [DF__emp_perso__date___07C12930]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_personal_detail] ADD  DEFAULT (NULL) FOR [date_of_birth]
GO
/****** Object:  Default [DF__emp_perso__marit__08B54D69]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_personal_detail] ADD  DEFAULT (NULL) FOR [marital_status]
GO
/****** Object:  Default [DF__emp_perso__perma__09A971A2]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_personal_detail] ADD  DEFAULT (NULL) FOR [permanent_address]
GO
/****** Object:  Default [DF__emp_perso__perma__0A9D95DB]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_personal_detail] ADD  DEFAULT (NULL) FOR [permanent_telephone]
GO
/****** Object:  Default [DF__emp_perso__prese__0B91BA14]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_personal_detail] ADD  DEFAULT (NULL) FOR [present_address]
GO
/****** Object:  Default [DF__emp_perso__prese__0C85DE4D]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_personal_detail] ADD  DEFAULT (NULL) FOR [present_address_telephone]
GO
/****** Object:  Default [DF__emp_perso__prese__0D7A0286]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_personal_detail] ADD  DEFAULT (NULL) FOR [present_address_mobile]
GO
/****** Object:  Default [DF__emp_perso__passp__0E6E26BF]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_personal_detail] ADD  DEFAULT (NULL) FOR [passport]
GO
/****** Object:  Default [DF__emp_perso__passp__0F624AF8]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_personal_detail] ADD  DEFAULT (NULL) FOR [passport_Expiry]
GO
/****** Object:  Default [DF__emp_perso__relev__10566F31]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_personal_detail] ADD  DEFAULT (NULL) FOR [relevant_experience]
GO
/****** Object:  Default [DF__emp_person__visa__114A936A]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_personal_detail] ADD  DEFAULT (NULL) FOR [visa]
GO
/****** Object:  Default [DF__emp_perso__visa___123EB7A3]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_personal_detail] ADD  DEFAULT (NULL) FOR [visa_Expiry]
GO
/****** Object:  Default [DF__emp_perso__onsit__1332DBDC]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_personal_detail] ADD  DEFAULT (NULL) FOR [onsite_Experience]
GO
/****** Object:  Default [DF__emp_perso__it_ex__14270015]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_personal_detail] ADD  DEFAULT (NULL) FOR [it_experience]
GO
/****** Object:  Default [DF__emp_perso__prima__151B244E]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_personal_detail] ADD  DEFAULT (NULL) FOR [primary_skill]
GO
/****** Object:  Default [DF__emp_perso__secon__160F4887]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_personal_detail] ADD  DEFAULT (NULL) FOR [secondary_skill]
GO
/****** Object:  Default [DF__emp_famil__appli__75A278F5]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_family_detail] ADD  DEFAULT (NULL) FOR [application_Id]
GO
/****** Object:  Default [DF__emp_famil__membe__76969D2E]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_family_detail] ADD  DEFAULT (NULL) FOR [member_name]
GO
/****** Object:  Default [DF__emp_famil__occup__778AC167]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_family_detail] ADD  DEFAULT (NULL) FOR [occupation]
GO
/****** Object:  Default [DF__emp_family___age__787EE5A0]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_family_detail] ADD  DEFAULT (NULL) FOR [age]
GO
/****** Object:  Default [DF__emp_famil__depen__797309D9]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_family_detail] ADD  DEFAULT (NULL) FOR [dependent]
GO
/****** Object:  Default [DF__emp_famil__prese__7A672E12]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_family_detail] ADD  DEFAULT (NULL) FOR [present_location]
GO
/****** Object:  Default [DF__emp_emplo__appli__6C190EBB]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_employment_detail] ADD  DEFAULT (NULL) FOR [application_id]
GO
/****** Object:  Default [DF__emp_emplo__emplo__6D0D32F4]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_employment_detail] ADD  DEFAULT (NULL) FOR [employer_name]
GO
/****** Object:  Default [DF__emp_emplo__locat__6E01572D]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_employment_detail] ADD  DEFAULT (NULL) FOR [location]
GO
/****** Object:  Default [DF__emp_emplo__numbe__6EF57B66]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_employment_detail] ADD  DEFAULT (NULL) FOR [number_of_employee]
GO
/****** Object:  Default [DF__emp_emplo__month__6FE99F9F]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_employment_detail] ADD  DEFAULT (NULL) FOR [months]
GO
/****** Object:  Default [DF__emp_emplo__years__70DDC3D8]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_employment_detail] ADD  DEFAULT (NULL) FOR [years]
GO
/****** Object:  Default [DF__emp_emplo__exper__71D1E811]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_employment_detail] ADD  DEFAULT (NULL) FOR [experience]
GO
/****** Object:  Default [DF__emp_emplo__desig__72C60C4A]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_employment_detail] ADD  DEFAULT (NULL) FOR [designation_held]
GO
/****** Object:  Default [DF__emp_emplo__repor__73BA3083]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_employment_detail] ADD  DEFAULT (NULL) FOR [reporting_to]
GO
/****** Object:  Default [DF__emp_emplo__reaso__74AE54BC]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_employment_detail] ADD  DEFAULT (NULL) FOR [reason_for_leaving]
GO
/****** Object:  Default [DF__emp_educa__appli__66603565]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_education_background] ADD  DEFAULT (NULL) FOR [application_id]
GO
/****** Object:  Default [DF__emp_educa__exami__6754599E]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_education_background] ADD  DEFAULT (NULL) FOR [examination_passed]
GO
/****** Object:  Default [DF__emp_educa__from___68487DD7]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_education_background] ADD  DEFAULT (NULL) FOR [from_to_date]
GO
/****** Object:  Default [DF__emp_educa__insti__693CA210]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_education_background] ADD  DEFAULT (NULL) FOR [institution]
GO
/****** Object:  Default [DF__emp_educa__board__6A30C649]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_education_background] ADD  DEFAULT (NULL) FOR [board]
GO
/****** Object:  Default [DF__emp_educa__marks__6B24EA82]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_education_background] ADD  DEFAULT (NULL) FOR [marks]
GO
/****** Object:  Default [DF__emp_curre__appli__60A75C0F]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_current_employment_detail] ADD  DEFAULT (NULL) FOR [application_id]
GO
/****** Object:  Default [DF__emp_curre__curre__619B8048]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_current_employment_detail] ADD  DEFAULT (NULL) FOR [current_project_name]
GO
/****** Object:  Default [DF__emp_curre__team___628FA481]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_current_employment_detail] ADD  DEFAULT (NULL) FOR [team_size]
GO
/****** Object:  Default [DF__emp_curre__skill__6383C8BA]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_current_employment_detail] ADD  DEFAULT (NULL) FOR [skills_used]
GO
/****** Object:  Default [DF__emp_curre__roles__6477ECF3]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_current_employment_detail] ADD  DEFAULT (NULL) FOR [roles_and_responsibilities]
GO
/****** Object:  Default [DF__emp_curre__organ__656C112C]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_current_employment_detail] ADD  DEFAULT (NULL) FOR [organisation_description]
GO
/****** Object:  Default [DF__emp__application__5812160E]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp] ADD  DEFAULT (NULL) FOR [application_id]
GO
/****** Object:  Default [DF__emp__emp_id__59063A47]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp] ADD  DEFAULT (NULL) FOR [emp_id]
GO
/****** Object:  Default [DF__emp__first_name__59FA5E80]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp] ADD  DEFAULT (NULL) FOR [first_name]
GO
/****** Object:  Default [DF__emp__last_name__5AEE82B9]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp] ADD  DEFAULT (NULL) FOR [last_name]
GO
/****** Object:  Default [DF__emp__email__5BE2A6F2]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp] ADD  DEFAULT (NULL) FOR [email]
GO
/****** Object:  Default [DF__emp__contact__5CD6CB2B]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp] ADD  DEFAULT (NULL) FOR [contact]
GO
/****** Object:  Default [DF__emp__address__5DCAEF64]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp] ADD  DEFAULT (NULL) FOR [address]
GO
/****** Object:  Default [DF__emp__zip__5EBF139D]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp] ADD  DEFAULT (NULL) FOR [zip]
GO
/****** Object:  Default [DF__emp__file_locati__5FB337D6]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp] ADD  DEFAULT (NULL) FOR [file_location]
GO
/****** Object:  Default [DF__hr_bgc__remark__3A4CA8FD]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[hr_bgc] ADD  DEFAULT (NULL) FOR [remark]
GO
/****** Object:  Default [DF__file_data__file___3493CFA7]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[file_data] ADD  DEFAULT (NULL) FOR [file_description_id]
GO
/****** Object:  Default [DF__file_data__file___3587F3E0]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[file_data] ADD  DEFAULT (NULL) FOR [file_location]
GO
/****** Object:  Default [DF__file_data__creat__367C1819]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[file_data] ADD  DEFAULT (NULL) FOR [creation_date]
GO
/****** Object:  Default [DF__file_data__last___37703C52]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[file_data] ADD  DEFAULT (NULL) FOR [last_modified_date]
GO
/****** Object:  Default [DF__file_data__remar__3864608B]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[file_data] ADD  DEFAULT (NULL) FOR [remark]
GO
/****** Object:  Default [DF__file_data__fileN__395884C4]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[file_data] ADD  DEFAULT (NULL) FOR [fileName]
GO
/****** Object:  ForeignKey [FK_T1_T2_Cascade]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[file_category]  WITH CHECK ADD  CONSTRAINT [FK_T1_T2_Cascade] FOREIGN KEY([application_id])
REFERENCES [dbo].[pre_emp] ([application_id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[file_category] CHECK CONSTRAINT [FK_T1_T2_Cascade]
GO
/****** Object:  ForeignKey [FK2]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[file_category]  WITH CHECK ADD  CONSTRAINT [FK2] FOREIGN KEY([application_id])
REFERENCES [dbo].[pre_emp] ([application_id])
GO
ALTER TABLE [dbo].[file_category] CHECK CONSTRAINT [FK2]
GO
/****** Object:  ForeignKey [FK3]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[file_category]  WITH CHECK ADD  CONSTRAINT [FK3] FOREIGN KEY([parent_category_id])
REFERENCES [dbo].[file_category] ([category_id])
GO
ALTER TABLE [dbo].[file_category] CHECK CONSTRAINT [FK3]
GO
/****** Object:  ForeignKey [FK1_emp_training_attended_application_id]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_training_attended]  WITH CHECK ADD  CONSTRAINT [FK1_emp_training_attended_application_id] FOREIGN KEY([application_id])
REFERENCES [dbo].[pre_emp] ([application_id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[emp_training_attended] CHECK CONSTRAINT [FK1_emp_training_attended_application_id]
GO
/****** Object:  ForeignKey [FK1_emp_skills_application_id]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_skills]  WITH CHECK ADD  CONSTRAINT [FK1_emp_skills_application_id] FOREIGN KEY([application_id])
REFERENCES [dbo].[pre_emp] ([application_id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[emp_skills] CHECK CONSTRAINT [FK1_emp_skills_application_id]
GO
/****** Object:  ForeignKey [FK2_emp_skill_id]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_skills]  WITH CHECK ADD  CONSTRAINT [FK2_emp_skill_id] FOREIGN KEY([skill_id])
REFERENCES [dbo].[skills] ([skill_id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[emp_skills] CHECK CONSTRAINT [FK2_emp_skill_id]
GO
/****** Object:  ForeignKey [FK1_emp_reference_application_id]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_reference]  WITH CHECK ADD  CONSTRAINT [FK1_emp_reference_application_id] FOREIGN KEY([application_id])
REFERENCES [dbo].[pre_emp] ([application_id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[emp_reference] CHECK CONSTRAINT [FK1_emp_reference_application_id]
GO
/****** Object:  ForeignKey [FK1_emp_data_application_id]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_personal_detail]  WITH CHECK ADD  CONSTRAINT [FK1_emp_data_application_id] FOREIGN KEY([application_id])
REFERENCES [dbo].[pre_emp] ([application_id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[emp_personal_detail] CHECK CONSTRAINT [FK1_emp_data_application_id]
GO
/****** Object:  ForeignKey [FK1_emp_family_detail_application_id]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_family_detail]  WITH CHECK ADD  CONSTRAINT [FK1_emp_family_detail_application_id] FOREIGN KEY([application_Id])
REFERENCES [dbo].[pre_emp] ([application_id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[emp_family_detail] CHECK CONSTRAINT [FK1_emp_family_detail_application_id]
GO
/****** Object:  ForeignKey [FK1_emp_employment_detail_application_id]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_employment_detail]  WITH CHECK ADD  CONSTRAINT [FK1_emp_employment_detail_application_id] FOREIGN KEY([application_id])
REFERENCES [dbo].[pre_emp] ([application_id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[emp_employment_detail] CHECK CONSTRAINT [FK1_emp_employment_detail_application_id]
GO
/****** Object:  ForeignKey [FK1_emp_education_background_application_id]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_education_background]  WITH CHECK ADD  CONSTRAINT [FK1_emp_education_background_application_id] FOREIGN KEY([application_id])
REFERENCES [dbo].[pre_emp] ([application_id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[emp_education_background] CHECK CONSTRAINT [FK1_emp_education_background_application_id]
GO
/****** Object:  ForeignKey [FK1_current_employment_detail_application_id]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp_current_employment_detail]  WITH CHECK ADD  CONSTRAINT [FK1_current_employment_detail_application_id] FOREIGN KEY([application_id])
REFERENCES [dbo].[pre_emp] ([application_id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[emp_current_employment_detail] CHECK CONSTRAINT [FK1_current_employment_detail_application_id]
GO
/****** Object:  ForeignKey [FK__pre_emp]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[emp]  WITH CHECK ADD  CONSTRAINT [FK__pre_emp] FOREIGN KEY([application_id])
REFERENCES [dbo].[pre_emp] ([application_id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[emp] CHECK CONSTRAINT [FK__pre_emp]
GO
/****** Object:  ForeignKey [FK_default_files_and_folders_document_type]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[default_files_and_folders]  WITH CHECK ADD  CONSTRAINT [FK_default_files_and_folders_document_type] FOREIGN KEY([document_type_id])
REFERENCES [dbo].[document_type] ([document_type_id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[default_files_and_folders] CHECK CONSTRAINT [FK_default_files_and_folders_document_type]
GO
/****** Object:  ForeignKey [FK_default_files_document_class]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[default_files_and_folders]  WITH CHECK ADD  CONSTRAINT [FK_default_files_document_class] FOREIGN KEY([class_id])
REFERENCES [dbo].[document_class] ([class_id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[default_files_and_folders] CHECK CONSTRAINT [FK_default_files_document_class]
GO
/****** Object:  ForeignKey [FK_user_role_user]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[user_role]  WITH CHECK ADD  CONSTRAINT [FK_user_role_user] FOREIGN KEY([username])
REFERENCES [dbo].[users] ([username])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[user_role] CHECK CONSTRAINT [FK_user_role_user]
GO
/****** Object:  ForeignKey [FK_user_role_user_role1]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[user_role]  WITH CHECK ADD  CONSTRAINT [FK_user_role_user_role1] FOREIGN KEY([role_id])
REFERENCES [dbo].[roles] ([role_id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[user_role] CHECK CONSTRAINT [FK_user_role_user_role1]
GO
/****** Object:  ForeignKey [FK_PIM_EDUCATIONAL_DETAILS_PIM_COLLEGE_MASTER]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[PIM_EDUCATIONAL_DETAILS]  WITH CHECK ADD  CONSTRAINT [FK_PIM_EDUCATIONAL_DETAILS_PIM_COLLEGE_MASTER] FOREIGN KEY([EDU_NAMEOFESTABLISHMENT_ID])
REFERENCES [dbo].[PIM_COLLEGE_MASTER] ([COLLEGE_ID])
GO
ALTER TABLE [dbo].[PIM_EDUCATIONAL_DETAILS] CHECK CONSTRAINT [FK_PIM_EDUCATIONAL_DETAILS_PIM_COLLEGE_MASTER]
GO
/****** Object:  ForeignKey [FK_PIM_EDUCATIONAL_DETAILS_PIM_DEGREE_MASTER]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[PIM_EDUCATIONAL_DETAILS]  WITH CHECK ADD  CONSTRAINT [FK_PIM_EDUCATIONAL_DETAILS_PIM_DEGREE_MASTER] FOREIGN KEY([EDU_TYPEOFESTABLISHMENT_ID])
REFERENCES [dbo].[PIM_DEGREE_MASTER] ([DEGREE_ID])
GO
ALTER TABLE [dbo].[PIM_EDUCATIONAL_DETAILS] CHECK CONSTRAINT [FK_PIM_EDUCATIONAL_DETAILS_PIM_DEGREE_MASTER]
GO
/****** Object:  ForeignKey [FK_PIM_EDUCATIONAL_DETAILS_PIM_DISCIPLINE_MASTER]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[PIM_EDUCATIONAL_DETAILS]  WITH CHECK ADD  CONSTRAINT [FK_PIM_EDUCATIONAL_DETAILS_PIM_DISCIPLINE_MASTER] FOREIGN KEY([EDU_DISCIPLINE_ID])
REFERENCES [dbo].[PIM_DISCIPLINE_MASTER] ([DISCIPLINE_ID])
GO
ALTER TABLE [dbo].[PIM_EDUCATIONAL_DETAILS] CHECK CONSTRAINT [FK_PIM_EDUCATIONAL_DETAILS_PIM_DISCIPLINE_MASTER]
GO
/****** Object:  ForeignKey [FK_PIM_EDUCATIONAL_DETAILS_PIM_DISCIPLINE_MASTER1]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[PIM_EDUCATIONAL_DETAILS]  WITH CHECK ADD  CONSTRAINT [FK_PIM_EDUCATIONAL_DETAILS_PIM_DISCIPLINE_MASTER1] FOREIGN KEY([EDU_MAJORFIELD_ID])
REFERENCES [dbo].[PIM_DISCIPLINE_MASTER] ([DISCIPLINE_ID])
GO
ALTER TABLE [dbo].[PIM_EDUCATIONAL_DETAILS] CHECK CONSTRAINT [FK_PIM_EDUCATIONAL_DETAILS_PIM_DISCIPLINE_MASTER1]
GO
/****** Object:  ForeignKey [FK_PIM_EDUCATIONAL_DETAILS_PIM_DISCIPLINE_MASTER2]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[PIM_EDUCATIONAL_DETAILS]  WITH CHECK ADD  CONSTRAINT [FK_PIM_EDUCATIONAL_DETAILS_PIM_DISCIPLINE_MASTER2] FOREIGN KEY([EDU_MINORFIELD_ID])
REFERENCES [dbo].[PIM_DISCIPLINE_MASTER] ([DISCIPLINE_ID])
GO
ALTER TABLE [dbo].[PIM_EDUCATIONAL_DETAILS] CHECK CONSTRAINT [FK_PIM_EDUCATIONAL_DETAILS_PIM_DISCIPLINE_MASTER2]
GO
/****** Object:  ForeignKey [FK_PIM_EDUCATIONAL_DETAILS_PIM_EDUCATIONAL_DETAILS]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[PIM_EDUCATIONAL_DETAILS]  WITH CHECK ADD  CONSTRAINT [FK_PIM_EDUCATIONAL_DETAILS_PIM_EDUCATIONAL_DETAILS] FOREIGN KEY([EDU_ID])
REFERENCES [dbo].[PIM_EDUCATIONAL_DETAILS] ([EDU_ID])
GO
ALTER TABLE [dbo].[PIM_EDUCATIONAL_DETAILS] CHECK CONSTRAINT [FK_PIM_EDUCATIONAL_DETAILS_PIM_EDUCATIONAL_DETAILS]
GO
/****** Object:  ForeignKey [FK_PIM_EDUCATIONAL_DETAILS_PIM_EDUCATIONAL_DETAILS1]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[PIM_EDUCATIONAL_DETAILS]  WITH CHECK ADD  CONSTRAINT [FK_PIM_EDUCATIONAL_DETAILS_PIM_EDUCATIONAL_DETAILS1] FOREIGN KEY([EDU_ID])
REFERENCES [dbo].[PIM_EDUCATIONAL_DETAILS] ([EDU_ID])
GO
ALTER TABLE [dbo].[PIM_EDUCATIONAL_DETAILS] CHECK CONSTRAINT [FK_PIM_EDUCATIONAL_DETAILS_PIM_EDUCATIONAL_DETAILS1]
GO
/****** Object:  ForeignKey [FK_PIM_EDUCATIONAL_DETAILS_PIM_LEVEL_MASTER]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[PIM_EDUCATIONAL_DETAILS]  WITH CHECK ADD  CONSTRAINT [FK_PIM_EDUCATIONAL_DETAILS_PIM_LEVEL_MASTER] FOREIGN KEY([EDU_LEVEL_ID])
REFERENCES [dbo].[PIM_LEVEL_MASTER] ([LEVEL_ID])
GO
ALTER TABLE [dbo].[PIM_EDUCATIONAL_DETAILS] CHECK CONSTRAINT [FK_PIM_EDUCATIONAL_DETAILS_PIM_LEVEL_MASTER]
GO
/****** Object:  ForeignKey [FK_PIM_EDUCATIONAL_DETAILS_PIM_SUBJECT_MASTER]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[PIM_EDUCATIONAL_DETAILS]  WITH CHECK ADD  CONSTRAINT [FK_PIM_EDUCATIONAL_DETAILS_PIM_SUBJECT_MASTER] FOREIGN KEY([EDU_SUBJECT_ID])
REFERENCES [dbo].[PIM_SUBJECT_MASTER] ([SUBJECT_ID])
GO
ALTER TABLE [dbo].[PIM_EDUCATIONAL_DETAILS] CHECK CONSTRAINT [FK_PIM_EDUCATIONAL_DETAILS_PIM_SUBJECT_MASTER]
GO
/****** Object:  ForeignKey [FK_PIM_EDUCATIONAL_DETAILS_PIM_UNIVERSITY_MASTER]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[PIM_EDUCATIONAL_DETAILS]  WITH CHECK ADD  CONSTRAINT [FK_PIM_EDUCATIONAL_DETAILS_PIM_UNIVERSITY_MASTER] FOREIGN KEY([EDU_AFFILIATEDTO_ID])
REFERENCES [dbo].[PIM_UNIVERSITY_MASTER] ([UNV_ID])
GO
ALTER TABLE [dbo].[PIM_EDUCATIONAL_DETAILS] CHECK CONSTRAINT [FK_PIM_EDUCATIONAL_DETAILS_PIM_UNIVERSITY_MASTER]
GO
/****** Object:  ForeignKey [FK_PIM_CONTACT_DETAILS_PIM_CONTACT_DETAILS]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[PIM_CONTACT_DETAILS]  WITH CHECK ADD  CONSTRAINT [FK_PIM_CONTACT_DETAILS_PIM_CONTACT_DETAILS] FOREIGN KEY([CONTACT_ID])
REFERENCES [dbo].[PIM_CONTACT_DETAILS] ([CONTACT_ID])
GO
ALTER TABLE [dbo].[PIM_CONTACT_DETAILS] CHECK CONSTRAINT [FK_PIM_CONTACT_DETAILS_PIM_CONTACT_DETAILS]
GO
/****** Object:  ForeignKey [FK__pre_emp1]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[hr_bgc]  WITH CHECK ADD  CONSTRAINT [FK__pre_emp1] FOREIGN KEY([application_id])
REFERENCES [dbo].[pre_emp] ([application_id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[hr_bgc] CHECK CONSTRAINT [FK__pre_emp1]
GO
/****** Object:  ForeignKey [FK_file_data_default_files_and_folders]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[file_data]  WITH CHECK ADD  CONSTRAINT [FK_file_data_default_files_and_folders] FOREIGN KEY([file_description_id])
REFERENCES [dbo].[default_files_and_folders] ([file_description_id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[file_data] CHECK CONSTRAINT [FK_file_data_default_files_and_folders]
GO
/****** Object:  ForeignKey [FK_file_data_file_category]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[file_data]  WITH CHECK ADD  CONSTRAINT [FK_file_data_file_category] FOREIGN KEY([parent_category_id])
REFERENCES [dbo].[file_category] ([category_id])
GO
ALTER TABLE [dbo].[file_data] CHECK CONSTRAINT [FK_file_data_file_category]
GO
/****** Object:  ForeignKey [FK_file_data_flags]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[file_data]  WITH CHECK ADD  CONSTRAINT [FK_file_data_flags] FOREIGN KEY([flag_id])
REFERENCES [dbo].[flags] ([flag_id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[file_data] CHECK CONSTRAINT [FK_file_data_flags]
GO
/****** Object:  ForeignKey [FK_file_data_pre_emp]    Script Date: 05/19/2015 15:39:38 ******/
ALTER TABLE [dbo].[file_data]  WITH CHECK ADD  CONSTRAINT [FK_file_data_pre_emp] FOREIGN KEY([application_id])
REFERENCES [dbo].[pre_emp] ([application_id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[file_data] CHECK CONSTRAINT [FK_file_data_pre_emp]
GO
