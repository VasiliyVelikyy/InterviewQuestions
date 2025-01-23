object Form1: TForm1
  Left = 404
  Top = 68
  Width = 670
  Height = 439
  Caption = #1056#1072#1073#1086#1090#1072' '#1089' '#1082#1086#1084#1087#1086#1085#1077#1085#1090#1086#1084' OracleDataSet'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object Button1: TButton
    Left = 416
    Top = 360
    Width = 225
    Height = 25
    Caption = #1057#1086#1093#1088#1072#1085#1080#1090#1100' '#1080#1079#1084#1077#1085#1077#1085#1080#1103
    TabOrder = 0
    OnClick = Button1Click
  end
  object DBEdit1: TDBEdit
    Left = 24
    Top = 256
    Width = 121
    Height = 21
    DataField = 'COUNTRY_ID'
    DataSource = DataSource1
    TabOrder = 1
  end
  object DBEdit2: TDBEdit
    Left = 168
    Top = 256
    Width = 121
    Height = 21
    DataField = 'COUNTRY_NAME'
    DataSource = DataSource1
    TabOrder = 2
  end
  object Button2: TButton
    Left = 408
    Top = 224
    Width = 75
    Height = 25
    Caption = #1042' '#1085#1072#1095#1072#1083#1086
    TabOrder = 3
    OnClick = Button2Click
  end
  object Button3: TButton
    Left = 496
    Top = 224
    Width = 75
    Height = 25
    Caption = #1044#1072#1083#1077#1077
    TabOrder = 4
    OnClick = Button3Click
  end
  object DBNavigator1: TDBNavigator
    Left = 24
    Top = 176
    Width = 600
    Height = 25
    DataSource = DataSource1
    TabOrder = 5
  end
  object DBGrid1: TDBGrid
    Left = 24
    Top = 48
    Width = 601
    Height = 120
    DataSource = DataSource1
    TabOrder = 6
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = 'MS Sans Serif'
    TitleFont.Style = []
    Columns = <
      item
        Expanded = False
        FieldName = 'COUNTRY_ID'
        Title.Caption = #1050#1086#1076' '#1089#1090#1088#1072#1085#1099
        Visible = True
      end
      item
        Expanded = False
        FieldName = 'COUNTRY_NAME'
        Title.Caption = #1057#1090#1088#1072#1085#1072
        Visible = True
      end
      item
        Expanded = False
        FieldName = 'REGION_ID'
        Title.Caption = #1050#1086#1076' '#1088#1077#1075#1080#1086#1085#1072' '
        Visible = True
      end>
  end
  object Button4: TButton
    Left = 552
    Top = 272
    Width = 75
    Height = 25
    Caption = #1054#1073#1085#1086#1074#1080#1090#1100
    TabOrder = 7
    OnClick = Button4Click
  end
  object OracleSession1: TOracleSession
    LogonUsername = 'hr'
    LogonPassword = 'hr'
    LogonDatabase = 'ORCL'
    Connected = True
    Left = 8
    Top = 8
  end
  object DataSource1: TDataSource
    DataSet = OracleDataSet1
    Left = 96
    Top = 8
  end
  object OracleDataSet1: TOracleDataSet
    SQL.Strings = (
      'select t.*, t.rowid from COUNTRIES t')
    QBEDefinition.QBEFieldDefs = {
      04000000030000000A000000434F554E5452595F49440100000000000C000000
      434F554E5452595F4E414D4501000000000009000000524547494F4E5F494401
      0000000000}
    Session = OracleSession1
    Active = True
    Left = 48
    Top = 8
  end
end
