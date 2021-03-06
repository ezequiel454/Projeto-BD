USE [master]
GO
/****** Object:  Database [SGAM]    Script Date: 30/01/2015 21:47:05 ******/
CREATE DATABASE [SGAM]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SGAM', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\SGAM.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'SGAM_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\SGAM_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [SGAM] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SGAM].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SGAM] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SGAM] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SGAM] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SGAM] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SGAM] SET ARITHABORT OFF 
GO
ALTER DATABASE [SGAM] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SGAM] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SGAM] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SGAM] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SGAM] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SGAM] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SGAM] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SGAM] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SGAM] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SGAM] SET  DISABLE_BROKER 
GO
ALTER DATABASE [SGAM] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SGAM] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SGAM] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SGAM] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SGAM] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SGAM] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SGAM] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SGAM] SET RECOVERY FULL 
GO
ALTER DATABASE [SGAM] SET  MULTI_USER 
GO
ALTER DATABASE [SGAM] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SGAM] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SGAM] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SGAM] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [SGAM] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'SGAM', N'ON'
GO
USE [SGAM]
GO
/****** Object:  Table [dbo].[Acesso]    Script Date: 30/01/2015 21:47:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Acesso](
	[idAcesso] [int] IDENTITY(1,1) NOT NULL,
	[dataAcesso] [datetime] NULL,
	[nomeTabelaAfetada] [varchar](255) NULL,
	[tipo] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[idAcesso] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Aluno]    Script Date: 30/01/2015 21:47:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Aluno](
	[profissao] [varchar](255) NULL,
	[turno] [varchar](255) NULL,
	[cpf] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[cpf] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Despesa]    Script Date: 30/01/2015 21:47:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Despesa](
	[idDespesa] [int] IDENTITY(1,1) NOT NULL,
	[descricao] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[idDespesa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[EnderecoPessoa]    Script Date: 30/01/2015 21:47:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[EnderecoPessoa](
	[idEndereco] [int] IDENTITY(1,1) NOT NULL,
	[bairro] [varchar](255) NULL,
	[cidade] [varchar](255) NULL,
	[complemento] [varchar](255) NULL,
	[estado] [varchar](255) NULL,
	[rua] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[idEndereco] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[EnderecoUnidade]    Script Date: 30/01/2015 21:47:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[EnderecoUnidade](
	[idEndereco] [int] IDENTITY(1,1) NOT NULL,
	[bairro] [varchar](255) NULL,
	[cidade] [varchar](255) NULL,
	[complemento] [varchar](255) NULL,
	[estado] [varchar](255) NULL,
	[rua] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[idEndereco] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Instrutor]    Script Date: 30/01/2015 21:47:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Instrutor](
	[cref] [varchar](255) NULL,
	[cpf] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[cpf] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[InstrutorModalidade]    Script Date: 30/01/2015 21:47:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[InstrutorModalidade](
	[idInstrutorModalidade] [int] IDENTITY(1,1) NOT NULL,
	[cpf] [varchar](255) NULL,
	[idModalidade] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[idInstrutorModalidade] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[InstrutorModalidadeAluno]    Script Date: 30/01/2015 21:47:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[InstrutorModalidadeAluno](
	[idInstrutorModalidadeAluno] [int] IDENTITY(1,1) NOT NULL,
	[cpf] [varchar](255) NULL,
	[idInstrutorModalidade] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[idInstrutorModalidadeAluno] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[InstrutorModalidadeUnidade]    Script Date: 30/01/2015 21:47:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[InstrutorModalidadeUnidade](
	[idInstrutorModalidadeUnidade] [int] IDENTITY(1,1) NOT NULL,
	[cargoInstrutor] [varchar](255) NULL,
	[dataContratacao] [datetime] NULL,
	[dataFimContrato] [datetime] NULL,
	[salarioInstrutor] [float] NOT NULL,
	[idInstrutorModalidade] [int] NULL,
	[nome] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[idInstrutorModalidadeUnidade] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[LogAcesso]    Script Date: 30/01/2015 21:47:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[LogAcesso](
	[idLogAcesso] [int] IDENTITY(1,1) NOT NULL,
	[idAcesso] [int] NULL,
	[login] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[idLogAcesso] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Modalidade]    Script Date: 30/01/2015 21:47:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Modalidade](
	[idModalidade] [int] IDENTITY(1,1) NOT NULL,
	[nome] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[idModalidade] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Pagamento]    Script Date: 30/01/2015 21:47:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Pagamento](
	[idPagamento] [int] IDENTITY(1,1) NOT NULL,
	[anoPago] [varchar](255) NULL,
	[dataPagamentoRealizado] [datetime] NULL,
	[desconto] [float] NOT NULL,
	[mesPago] [varchar](255) NULL,
	[valorPago] [float] NOT NULL,
	[idUnidadeAluno] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[idPagamento] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Pessoa]    Script Date: 30/01/2015 21:47:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Pessoa](
	[cpf] [varchar](255) NOT NULL,
	[dataNascimento] [datetime] NULL,
	[email] [varchar](255) NULL,
	[estadoCivil] [varchar](255) NULL,
	[identidade] [varchar](255) NULL,
	[nacionalidade] [varchar](255) NULL,
	[naturalidade] [varchar](255) NULL,
	[nome] [varchar](255) NULL,
	[sexo] [varchar](255) NULL,
	[idEndereco] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[cpf] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[TelefonePessoa]    Script Date: 30/01/2015 21:47:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TelefonePessoa](
	[idTelefone] [int] IDENTITY(1,1) NOT NULL,
	[numero] [varchar](255) NULL,
	[cpf] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[idTelefone] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[TelefoneUnidade]    Script Date: 30/01/2015 21:47:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TelefoneUnidade](
	[idTelefone] [int] IDENTITY(1,1) NOT NULL,
	[numero] [varchar](255) NULL,
	[nome] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[idTelefone] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Unidade]    Script Date: 30/01/2015 21:47:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Unidade](
	[nome] [varchar](255) NOT NULL,
	[cnpj] [varchar](255) NULL,
	[idEndereco] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[nome] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[UnidadeAluno]    Script Date: 30/01/2015 21:47:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[UnidadeAluno](
	[idUnidadeAluno] [int] IDENTITY(1,1) NOT NULL,
	[dataMatricula] [datetime] NULL,
	[diaPagamento] [varchar](255) NULL,
	[numMatricula] [int] NOT NULL,
	[scanAutorizacaoResponsavel] [varchar](255) NULL,
	[scanAvaliacaoFisica] [varchar](255) NULL,
	[scanQuestionario] [varchar](255) NULL,
	[situacaoPagamento] [varchar](255) NULL,
	[cpf] [varchar](255) NULL,
	[nome] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[idUnidadeAluno] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[UnidadeDespesa]    Script Date: 30/01/2015 21:47:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[UnidadeDespesa](
	[idUnidadeDespesa] [int] IDENTITY(1,1) NOT NULL,
	[data] [datetime] NULL,
	[valor] [float] NOT NULL,
	[idDespesa] [int] NULL,
	[nome] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[idUnidadeDespesa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[UnidadeModalidade]    Script Date: 30/01/2015 21:47:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[UnidadeModalidade](
	[idUnidadeModalidade] [int] IDENTITY(1,1) NOT NULL,
	[dataInicio] [datetime] NULL,
	[datafim] [datetime] NULL,
	[valorMensalidade] [float] NOT NULL,
	[idModalidade] [int] NULL,
	[nome] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[idUnidadeModalidade] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[UnidadeUsuario]    Script Date: 30/01/2015 21:47:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[UnidadeUsuario](
	[idUnidadeUsuario] [int] IDENTITY(1,1) NOT NULL,
	[nome] [varchar](255) NULL,
	[login] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[idUnidadeUsuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Usuario]    Script Date: 30/01/2015 21:47:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Usuario](
	[login] [varchar](255) NOT NULL,
	[email] [varchar](255) NULL,
	[senha] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[login] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[Aluno]  WITH CHECK ADD  CONSTRAINT [FK_g6otv1ccqwf8a15re4tc1sr9q] FOREIGN KEY([cpf])
REFERENCES [dbo].[Pessoa] ([cpf])
GO
ALTER TABLE [dbo].[Aluno] CHECK CONSTRAINT [FK_g6otv1ccqwf8a15re4tc1sr9q]
GO
ALTER TABLE [dbo].[Instrutor]  WITH CHECK ADD  CONSTRAINT [FK_rsp5ggtjn5k2cvwqpxroyt6b4] FOREIGN KEY([cpf])
REFERENCES [dbo].[Pessoa] ([cpf])
GO
ALTER TABLE [dbo].[Instrutor] CHECK CONSTRAINT [FK_rsp5ggtjn5k2cvwqpxroyt6b4]
GO
ALTER TABLE [dbo].[InstrutorModalidade]  WITH CHECK ADD  CONSTRAINT [FK_3eb3t0cmdthxelbco4s7lr1aq] FOREIGN KEY([cpf])
REFERENCES [dbo].[Instrutor] ([cpf])
GO
ALTER TABLE [dbo].[InstrutorModalidade] CHECK CONSTRAINT [FK_3eb3t0cmdthxelbco4s7lr1aq]
GO
ALTER TABLE [dbo].[InstrutorModalidade]  WITH CHECK ADD  CONSTRAINT [FK_oajqu6wysav187kc871nvpi81] FOREIGN KEY([idModalidade])
REFERENCES [dbo].[Modalidade] ([idModalidade])
GO
ALTER TABLE [dbo].[InstrutorModalidade] CHECK CONSTRAINT [FK_oajqu6wysav187kc871nvpi81]
GO
ALTER TABLE [dbo].[InstrutorModalidadeAluno]  WITH CHECK ADD  CONSTRAINT [FK_2fbdlsf66c3rf2tjigf2ry55h] FOREIGN KEY([cpf])
REFERENCES [dbo].[Aluno] ([cpf])
GO
ALTER TABLE [dbo].[InstrutorModalidadeAluno] CHECK CONSTRAINT [FK_2fbdlsf66c3rf2tjigf2ry55h]
GO
ALTER TABLE [dbo].[InstrutorModalidadeAluno]  WITH CHECK ADD  CONSTRAINT [FK_l3yrp38a7hb3ao7ietbpxbjan] FOREIGN KEY([idInstrutorModalidade])
REFERENCES [dbo].[InstrutorModalidade] ([idInstrutorModalidade])
GO
ALTER TABLE [dbo].[InstrutorModalidadeAluno] CHECK CONSTRAINT [FK_l3yrp38a7hb3ao7ietbpxbjan]
GO
ALTER TABLE [dbo].[InstrutorModalidadeUnidade]  WITH CHECK ADD  CONSTRAINT [FK_a6xv312f6ihed7n2q62hhjsib] FOREIGN KEY([idInstrutorModalidade])
REFERENCES [dbo].[InstrutorModalidade] ([idInstrutorModalidade])
GO
ALTER TABLE [dbo].[InstrutorModalidadeUnidade] CHECK CONSTRAINT [FK_a6xv312f6ihed7n2q62hhjsib]
GO
ALTER TABLE [dbo].[InstrutorModalidadeUnidade]  WITH CHECK ADD  CONSTRAINT [FK_r0ck3a8mbn1xsm9uirk0jkc6x] FOREIGN KEY([nome])
REFERENCES [dbo].[Unidade] ([nome])
GO
ALTER TABLE [dbo].[InstrutorModalidadeUnidade] CHECK CONSTRAINT [FK_r0ck3a8mbn1xsm9uirk0jkc6x]
GO
ALTER TABLE [dbo].[LogAcesso]  WITH CHECK ADD  CONSTRAINT [FK_3lyp8fs9p8v99e662b99rn78n] FOREIGN KEY([login])
REFERENCES [dbo].[Usuario] ([login])
GO
ALTER TABLE [dbo].[LogAcesso] CHECK CONSTRAINT [FK_3lyp8fs9p8v99e662b99rn78n]
GO
ALTER TABLE [dbo].[LogAcesso]  WITH CHECK ADD  CONSTRAINT [FK_f0px5np0xk9vcr72yrs9f8oso] FOREIGN KEY([idAcesso])
REFERENCES [dbo].[Acesso] ([idAcesso])
GO
ALTER TABLE [dbo].[LogAcesso] CHECK CONSTRAINT [FK_f0px5np0xk9vcr72yrs9f8oso]
GO
ALTER TABLE [dbo].[Pagamento]  WITH CHECK ADD  CONSTRAINT [FK_bk3h020ojp4fjgec04u509ime] FOREIGN KEY([idUnidadeAluno])
REFERENCES [dbo].[UnidadeAluno] ([idUnidadeAluno])
GO
ALTER TABLE [dbo].[Pagamento] CHECK CONSTRAINT [FK_bk3h020ojp4fjgec04u509ime]
GO
ALTER TABLE [dbo].[Pessoa]  WITH CHECK ADD  CONSTRAINT [FK_52gqpacqp8ty7udg7kt1mghal] FOREIGN KEY([idEndereco])
REFERENCES [dbo].[EnderecoPessoa] ([idEndereco])
GO
ALTER TABLE [dbo].[Pessoa] CHECK CONSTRAINT [FK_52gqpacqp8ty7udg7kt1mghal]
GO
ALTER TABLE [dbo].[TelefonePessoa]  WITH CHECK ADD  CONSTRAINT [FK_5cun48w0yva6uggud6rkb5cnr] FOREIGN KEY([cpf])
REFERENCES [dbo].[Pessoa] ([cpf])
GO
ALTER TABLE [dbo].[TelefonePessoa] CHECK CONSTRAINT [FK_5cun48w0yva6uggud6rkb5cnr]
GO
ALTER TABLE [dbo].[TelefoneUnidade]  WITH CHECK ADD  CONSTRAINT [FK_c0ix5soyigwaa416uravsd6wc] FOREIGN KEY([nome])
REFERENCES [dbo].[Unidade] ([nome])
GO
ALTER TABLE [dbo].[TelefoneUnidade] CHECK CONSTRAINT [FK_c0ix5soyigwaa416uravsd6wc]
GO
ALTER TABLE [dbo].[Unidade]  WITH CHECK ADD  CONSTRAINT [FK_eql044q3nunu4p71lu9nebw3w] FOREIGN KEY([idEndereco])
REFERENCES [dbo].[EnderecoUnidade] ([idEndereco])
GO
ALTER TABLE [dbo].[Unidade] CHECK CONSTRAINT [FK_eql044q3nunu4p71lu9nebw3w]
GO
ALTER TABLE [dbo].[UnidadeAluno]  WITH CHECK ADD  CONSTRAINT [FK_e08vmc7ery6aylwt4nabub25r] FOREIGN KEY([cpf])
REFERENCES [dbo].[Aluno] ([cpf])
GO
ALTER TABLE [dbo].[UnidadeAluno] CHECK CONSTRAINT [FK_e08vmc7ery6aylwt4nabub25r]
GO
ALTER TABLE [dbo].[UnidadeAluno]  WITH CHECK ADD  CONSTRAINT [FK_pm3rwawlh9i6k1p3usuktdp87] FOREIGN KEY([nome])
REFERENCES [dbo].[Unidade] ([nome])
GO
ALTER TABLE [dbo].[UnidadeAluno] CHECK CONSTRAINT [FK_pm3rwawlh9i6k1p3usuktdp87]
GO
ALTER TABLE [dbo].[UnidadeDespesa]  WITH CHECK ADD  CONSTRAINT [FK_mcqfxlxquqapfq6k6vb5222tr] FOREIGN KEY([nome])
REFERENCES [dbo].[Unidade] ([nome])
GO
ALTER TABLE [dbo].[UnidadeDespesa] CHECK CONSTRAINT [FK_mcqfxlxquqapfq6k6vb5222tr]
GO
ALTER TABLE [dbo].[UnidadeDespesa]  WITH CHECK ADD  CONSTRAINT [FK_sffdcdg3swokw5sejq8qo9tsi] FOREIGN KEY([idDespesa])
REFERENCES [dbo].[Despesa] ([idDespesa])
GO
ALTER TABLE [dbo].[UnidadeDespesa] CHECK CONSTRAINT [FK_sffdcdg3swokw5sejq8qo9tsi]
GO
ALTER TABLE [dbo].[UnidadeModalidade]  WITH CHECK ADD  CONSTRAINT [FK_dqlto2db9pk441w93k5ag36jm] FOREIGN KEY([nome])
REFERENCES [dbo].[Unidade] ([nome])
GO
ALTER TABLE [dbo].[UnidadeModalidade] CHECK CONSTRAINT [FK_dqlto2db9pk441w93k5ag36jm]
GO
ALTER TABLE [dbo].[UnidadeModalidade]  WITH CHECK ADD  CONSTRAINT [FK_sid08kbn4wr8t2tx11cmt1b3s] FOREIGN KEY([idModalidade])
REFERENCES [dbo].[Modalidade] ([idModalidade])
GO
ALTER TABLE [dbo].[UnidadeModalidade] CHECK CONSTRAINT [FK_sid08kbn4wr8t2tx11cmt1b3s]
GO
ALTER TABLE [dbo].[UnidadeUsuario]  WITH CHECK ADD  CONSTRAINT [FK_bghhm1b3ehk4x4lhbg0k9wssi] FOREIGN KEY([nome])
REFERENCES [dbo].[Unidade] ([nome])
GO
ALTER TABLE [dbo].[UnidadeUsuario] CHECK CONSTRAINT [FK_bghhm1b3ehk4x4lhbg0k9wssi]
GO
ALTER TABLE [dbo].[UnidadeUsuario]  WITH CHECK ADD  CONSTRAINT [FK_djfs2oulrssbr040vdkhpuc9j] FOREIGN KEY([login])
REFERENCES [dbo].[Usuario] ([login])
GO
ALTER TABLE [dbo].[UnidadeUsuario] CHECK CONSTRAINT [FK_djfs2oulrssbr040vdkhpuc9j]
GO
USE [master]
GO
ALTER DATABASE [SGAM] SET  READ_WRITE 
GO
