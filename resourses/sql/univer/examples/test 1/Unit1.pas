unit Unit1;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Oracle, DB, OracleData, ExtCtrls, DBCtrls, StdCtrls, Mask,
  Grids, DBGrids;

type
  TForm1 = class(TForm)
    OracleSession1: TOracleSession;
    Button1: TButton;
    DBEdit1: TDBEdit;
    DBEdit2: TDBEdit;
    Button2: TButton;
    Button3: TButton;
    DBNavigator1: TDBNavigator;
    DataSource1: TDataSource;
    OracleDataSet1: TOracleDataSet;
    DBGrid1: TDBGrid;
    Button4: TButton;
    procedure Button3Click(Sender: TObject);
    procedure Button2Click(Sender: TObject);
    procedure Button1Click(Sender: TObject);
    procedure Button4Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form1: TForm1;

implementation

{$R *.dfm}

procedure TForm1.Button3Click(Sender: TObject);
begin
OracleDataSet1.Next;
end;

procedure TForm1.Button2Click(Sender: TObject);
begin
OracleDataSet1.First;
end;

procedure TForm1.Button1Click(Sender: TObject);
begin
OracleDataSet1.Edit;
OracleDataSet1.Post;


end;

procedure TForm1.Button4Click(Sender: TObject);
begin

OracleDataSet1.Active:=False;
OracleDataSet1.Active:=True;
end;

end.
