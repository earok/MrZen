DEFTYPE.w

NEWTYPE.fx
x.w:y
u.w:v
id.w:a:z:hm
End NEWTYPE

#devo=0

WBStartup
CloseEd
NoCli
Dim maparray.b(17152)
Dim hdr.w(30)
Dim copa.l(11)
Dim ns(1),ne(1),nn(1),nw(1),zcx(1),zcy(1),zx(1),zy(1)
Dim typ(15),sine(15)
Dim r.b(26),g.b(26),b.b(26),bmc(3)
Dim n$(19),p0(1),pips(1),hit(1)
Dim List eb.fx(5)
Dim List alien.fx(17)
Dim ga0(19),gspd(19),ghits(15),gtile(15),gbul(19),gun.b(15)
Dim abob(19),xbob(19),ybob(19)
Dim swpos(15),swadr(15),sww(15),swh(15),swset(19)
Dim drpx(7),drpy(7),drx(7),dry(7),drlev(7)
Dim swinf.b(15,15)
Dim token.fx(15)
Dim xh(2),yh(2),zkarma(4)
Dim men$(4)
Dim sfx(3),vsfx(3)
n.w=0

For i=1 To 7:Read n$(i):Next
Data$ "tzc","ert","bod","hel","rem","anx","cjk"

BitMap 0,304,240,3
BitMap 1,304,240,3
BitMap 2,512,384,3
BitMap 3,256,16,2
BitMap 4,272,16,2
BitMap 6,32,26,2:Use BitMap 6:Boxf 5,5,25,21,2:Boxf 7,7,23,19,1
BitMap 7,144,96,3;collision bitmap
Buffer 0,7168
Buffer 1,7168
Buffer 2,16384
Buffer 3,16384
Queue 0,2
LoadShapes 245,293,"Movement/res.shp"
LoadSprites 0,16,"Movement/rose.spr"
LoadSound 1,"Breath/SWITCH"
LoadSound 2,"Breath/HUN"
LoadSound 3,"Breath/SWISH"
LoadSound 4,"Breath/THUD"
LoadSound 5,"Breath/BOUNCE"
LoadSound 6,"Breath/BIGX"
LoadSound 8,"Breath/SMALLX1"
LoadSound 7,"Breath/SMALLX2"
LoadSound 9,"Breath/PING"
LoadSound 10,"Breath/TPORT"
LoadSound 11,"Breath/PUFF"
LoadSound 12,"Breath/ZAP1"
LoadSound 13,"Breath/ZAP2"
LoadSound 14,"Breath/FOOTSTEP"
LoadSound 15,"Breath/SHOOT"
LoadSound 16,"Breath/SCREAM"
LoadSound 17,"Breath/FART"
VWait 250

BLITZ
Gosub slate

Gosub initplat
Repeat
Gosub game
Until 0
Free BitMap0:Free BitMap 1:Free BitMap 2
End

.slate
Slice 0,62,256,192,$fffa,6,8,28,304,512
Use Palette 0
ShowF 1,16+nzx,16+nzy,bx
ShowB 2,bx,by,16+nzx
Slice 1,44,256,16,$fffa,4,8,28,272,256
Use Slice 1
ShowF 4,0,0,0
ShowB 3,0,0,0
RGB 1,15,15,8:RGB 2,15,10,0
Use Slice 0
;ShowSprite 8,264,24,0
;ShowSprite 8,264,56,2
Use BitMap 2:Cls 0
RGB 19,0,0,0:RGB 18,8,0,0:RGB 17,14,2,6:RGB 16,0,0,0
RGB 23,0,0,0:RGB 22,8,0,8:RGB 21,12,0,15:RGB 20,0,0,0
RGB 27,0,9,0:RGB 26,0,15,0:RGB 25,7,15,7:RGB 24,0,0,0
RGB 7,0,0,0:PalRGB 0,0,0,0,0:RGB 0,0,0,0
For i=8 To 15:RGB i,0,0,0:Next
For i=1 To 11
 CustomCop Mki$($19E)+Mki$(i*$111),(i ASL 4)+64
Next
;Locate adresses in copper list for rainbow colours
For i=0 To CopLen Step 4
 cadr.l=CopLoc+i
 For j=1 To 11
  If Peek.w(cadr)=$19E AND Peek.w(cadr+2)=j*$111 Then copa(j)=cadr+2
 Next
Next
Return

Function.w trans{a}
Select a
Case 46:Function Return 282
Case 33:Function Return 283
Case 63:Function Return 284
Case 44:Function Return 285
Default
 If a>47 AND a<58 Then Function Return 197+a
 If a>64 AND a<91 Then Function Return 191+a
End Select
Function Return 282
End Function

.game
QAMIGA
LoadBitMap 0,"Stillness/Frontspiece",0
LoadShapes 372,377,"Movement/cal.shp"
VWait 250
BLITZ
BlitMode CookieMode
Use BitMap 2:Cls 0:Use BitMap 3:Cls 0:Use BitMap 4:Cls 0
For i=0 To 4 Step 2:ShowSprite 8,0,64,i:Next
For i=0 To 3:FlushBuffer i:Next
Use Slice 0:ShowF 0,16,16,16:ShowB 2,16,16,16:Use Palette 0:db=0
RGB 19,0,0,0:RGB 18,8,0,0:RGB 17,14,2,6:RGB 16,0,0,0
RGB 23,0,0,0:RGB 22,8,0,8:RGB 21,12,0,15:RGB 20,0,0,0
RGB 27,0,9,0:RGB 26,0,15,0:RGB 25,7,15,7:RGB 24,0,0,0
Use BitMap 1:Scroll 0,0,304,240,0,0,0
For i=0 To 5
 Sound 10,1,63
 xmin=48+i*32
 For x=268 To xmin Step -4
  db=1-db:UnBuffer db:Use BitMap db:BBlit db,372+i,x,148:VWait:ShowF db,16,16,16
 Next
 FlushBuffer db:UnBuffer 1-db:Use BitMap 1-db:Scroll 0,0,304,240,0,0,db
Next
ms$=""
Repeat
 Use Slice 1
 Use BitMap 4
 If Len(ms$)>1
  Blit trans{Asc(Left$(ms$,1))},256,4
  ms$=UnRight$(ms$,1)
 Else
  ms$="MR ZEN, PROGRAMMED BY MIKE COOPER IN BLITZ BASIC 2................."
  ms$+"AMIGA FORMAT COMPETITION DEMO CAN BE FREELY DISTRIBUTED, BUT ALL COMPONENT GRAPHICS AND CODE ARE COPYRIGHT............"
  m$+"PUSH FIRE TO START..........................."
 EndIf
 For i=0 To 7
  VWait 2
  If i=0 Then Scroll 8,0,264,16,0,0
  ShowF 4,i,0,0:ShowB 3,0,0,i
 Next
Until Joyb(1)<>0
For i=1 To 4:zkarma(i)=16:Next
For i=0 To 7:swset(i)=0:Next
lives=4
Repeat
friend=0:lives-1
nzcx=5:nzcy=19
nlev=1:lev=0
body=0:bodyflag=1:spell=0
Repeat
olev=lev:If lev<>nlev Then lev=nlev:a$=n$(lev):Gosub levload:If bodyflag=0 Then Gosub bodyload
On lev Gosub tzc,hel,hel,hel,rem,anx,cjk
Until pips(0)=0 OR pips(1)=0 OR spell=3
Until lives=0
Return

Statement noise{ch,smp,dist}
Dim sfx(3),vsfx(3)
SHARED sfx(),vsfx()
If dist<vsfx(ch)
 vsfx(ch)=dist
 sfx(ch)=smp
EndIf
End Statement

.tzc
For i=388 To 391:CopyShape i,i+4:Next
ga0(4)=388:ghits(4)=8:gspd(4)=1:gtile(4)=0:gbul(4)=0;cleaner
ga0(0)=396:ghits(0)=8:gspd(0)=3:gtile(0)=0:gbul(0)=0;radar
ga0(1)=400:ghits(1)=8:gspd(1)=0:gtile(1)=0:gbul(1)=2;gate
ga0(16)=404:gspd(16)=8:sbul=2;bullet
For i=0 To 15:CopyShape gtile(i),i*16+4:Next
Data.w -1,0
Data.w 80,312,10,58,4
bmc(1)=$eee:bmc(2)=$08e:bmc(3)=$00c
monadr=-1:pips(0)=8:pips(1)=8:p0(0)=1:p0(1)=1:magic=0:friend=0
If zkarma(4)<16 Then maparray(488)=0:maparray(489)=0:maparray(520)=65:maparray(521)=81
If zkarma(3)<16 Then maparray(472)=0:maparray(473)=0:maparray(504)=65:maparray(505)=81
If zkarma(2)<16 Then maparray(533)=0:maparray(534)=0:maparray(565)=65:maparray(566)=81
If zkarma(1)<16 Then maparray(733)=0:maparray(734)=0:maparray(765)=65:maparray(766)=81
zdir=372:Gosub veil_of_maya:m$="SELECT A BODY!"
If lives=0
 m$="PICK THE BODY OF YOUR CHOICE FROM THE LOTUS FLOWERS......."
 m$="GO ON,.......IF YOU NEED ANY HELP ASK OMEGA, IM SURE HELL BE GLAD TO OBLIGE..............."
EndIf
za0=372:zu=0:zv=-2
Use Slice 0:RGB 9,15,15,8:RGB 10,15,10,0
Repeat
If Joyr(1)=8
 zu=QLimit(zu+2*Sgn(zu),-6,6)
 zv=QLimit(zv+2*Sgn(zv),-6,6)
Else
 zu=QLimit(zu+4*Joyx(1),-6,6)
 zv=QLimit(zv+4*Joyy(1),-6,6)
EndIf
If Joyr(1)<>8 Then za0=372+Joyr(1)
za=za0:If (cyc&1=1) Then za+8:sfx(0)=10
zmx=zcx(db) ASL 4:zmy=zcy(db) ASL 4
nzx=zx(db)+zu:nzy=zy(db)+zv
zmx+nzx:zmx&$fff0:zmy+nzy:zmy&$fff0
Gosub generic
Gosub display
If maparray(zadr)&15=2
 If maparray(zadr+1)&15=2 AND zx(db)>7 Then gate=-1
 If maparray(zadr-1)&15=2 AND zx(db)<8 Then gate=-1
EndIf
Until gate=-1
gate=0:magic=0
zu=0:zv=0:Gosub display:UnBuffer db:Gosub display:UnBuffer db
body=maparray(zadr)ASR 5:If body=0 Then body=4
zkarma(body)=0
Gosub bodyload
lflag=1:If maparray(zadr-1)&15=2 Then lflag=0
For i=0 To 1
 db=1-db:Use BitMap db
 UnBuffer db
 If lflag=1
  Block 0,160,128:Block 0,144,128:Block 81,160,144:Block 65,144,144
  maparray(zadr)=0:maparray(zadr+1)=0:maparray(zadr+33)=81:maparray(zadr+32)=65
 Else
  Block 0,128,128:Block 0,144,128:Block 65,128,144:Block 81,144,144
  maparray(zadr)=0:maparray(zadr-1)=0:maparray(zadr+31)=65:maparray(zadr+32)=81
 EndIf
 ShowF db,16+nzx,16+nzy,bx
 ShowB 2,bx,by,16+nzx
Next
For i=0 To 15
USEPATH token(i)
\x=144:\y=128
\u=Rnd(9)-5:\v=-(Rnd(5)*2+12)
\id=i&3:If \id=3 Then \id=2
Next
ntoken=15:ttime=32
zdir=298:If zu<0 Then zdir=320:zu=2*Sgn(zu):zv=-8
Sound 6,1,63
Repeat
If pips(0)>0 AND pips(1)>0
Select spell
Case 0
If icadr<>-1 Then m0.b=maparray(icadr):maparray(icadr)=6
If air=1 Then Gosub air Else Gosub ground
If icadr<>-1 Then maparray(icadr)=m0
Case 1:Gosub teleport
Case 2:Gosub shrink
End Select
If spell=0 Then Gosub zmagic
EndIf
Gosub fxevo
Gosub generic
Gosub display
If zadr=216 AND air=0 AND zx(db)=0 Then Gosub omega
Until gate<>0
Return

Statement write{x,y,a$,hflag}
SHARED bx,by
Use BitMap 2
x0=bx+56+x ASL 3:y0=by+24+y ASL 3
For i=1 To Len(a$):Blit trans{Asc(Mid$(a$,i,1))},x0+i ASL 3,y0,hflag:Next
End Statement

Statement text{a$}
SHARED bx,by
Use BitMap 2
x0=bx+64:y0=by+120
For i=0 To 15:Blit trans{Asc(Mid$(a$,i+1,1))},x0+i ASL 3,y0,0:Next
For i=0 To 3
Scroll bx+64,by+34,128,104,bx+64,by+32
VWait 8
Next
End Statement

Statement clrom{n}
SHARED bx,by
Use BitMap 2
For i=0 To n ASL 2
Scroll bx+64,by+34,128,104,bx+64,by+32
VWait 8
Next
End Statement

.omega
Use Slice 0:RGB 13,15,15,15:RGB 14,10,10,15
za0=406+body*3:za=za0:zu=0:zv=0
magic=0:nbob=0:ClearList eb()
Gosub display:Gosub display:BlitMode CookieMode
Use BitMap 2:Boxf 48+bx,16+by,207+bx,143+by,0
For k=0 To 1:db=1-db
Use BitMap db:UnBuffer db:Boxf 80,48,207,143,0:Block 199,128,128:Block 215,144,128:BBlit db,za0,144,128
VWait:ShowF db,16,16,bx:Next
Restore omega
For i=1 To 4:Read men$(i):Next
Data$"WHO IS MR.ZEN?","DREAM TRACK","WHEEL OF THINGS","EXIT"
zenu=4
Repeat
 For i=1 To 4
  hflag=0:If zenu=i Then hflag=1
  write{1,i*2-1,men$(i),hflag}
 Next
 Repeat
  If Joyy(1)<>0
   ozenu=zenu:zenu=QLimit(zenu+Joyy(1),1,4)
   write{1,ozenu*2-1,men$(ozenu),0}
   write{1,zenu*2-1,men$(zenu),1}
   VWait 25
  EndIf
  db=1-db:Use BitMap db:UnBuffer db:BBlit db,za0+Joyx(1),144,128:VWait:ShowF db,16,16,bx
 Until Joyx(1)=-1
 Use BitMap 2:Boxf 48+bx,16+by,207+bx,143+by,0
 Select zenu
  Case 1
   text{"AS A CUSTOMER IN"}:text{"AN  EXOTIC  SHOP"}
   text{"MAY TRY ON  MANY"}:text{"COSTUMES, SO CAN"}
   text{"A SPIRIT  CLOTHE"}:text{"ITSELF  IN  MANY"}
   text{"BODIES.  ZEN WAS"}:text{"ONCE  JUST PLAIN"}
   text{"OLD BENN,BUT HIS"}:text{"SPIRIT  REMAINED"}
   text{"WITHIN THE WHEEL"}:text{"OF THINGS,    TO"}
   text{"CONTINUE TO HELP"}:text{"THE  INHABITANTS"}
   text{"OF EARTH.       "}:clrom{2}
   text{"   REINCARNATRON"}:text{"CIRCUITRY  TAKES"}
   text{"HIM TO TRANS ZEN"}:text{"CENTRAL ON DEATH"}
   text{"TO INHABIT A NEW"}:text{"BODY. THE BODIES"}
   text{"FEED  ON  KARMA,"}:text{"DRAWN  FROM  THE"}
   text{"INHABITANTS  OF "}:text{"EARTH..........."}
   clrom{2}
   text{"    VIOLENCE AND"}:text{"MEDIOCRITY  HAVE"}
   text{"DULLED  THE FINE"}:text{"QUALITY  OF THIS"}
   text{"KARMA OF LATE.  "}:text{"POLLUTION, FREAK"}
   text{"STORMS AND A NEW"}:text{"LURID DRUG NAMED"}
   text{"VOID  CAUSE  THE"}:text{"SPIRIT  OF   THE"}
   text{"EARTH TO  WITHER"}:text{"AND DIE........."}:clrom{2}
   text{"TAKE ON THE ROLE"}:text{"OF ZEN,AND BRING"}
   text{"ENLIGHTENMENT TO"}:text{"THE  PEOPLE   OF"}
   text{"EARTH.          "}
   clrom{4}
   Select body
    Case 1
     text{"THE  HOLOGRAPHIC"}:text{"BODY IS FAST,BUT"}
     text{"FRAGILE.  IT CAN"}:text{"DISASSEMBLE  ITS"}
     text{"MATRIX   BRIEFLY"}:text{"FOR  SHORT RANGE"}
     text{"TELEPORTATION.  "}:clrom{11}
    Case 2
     text{"THE  ATOMIC BODY"}:text{"FIRES DREAM DUST"}
     text{"HYPNOTISING SOME"}:text{"LIFEFORMS,   AND"}
     text{"DAMAGING OTHERS."}:text{"IT CAN SHRINK TO"}
     text{"ATOMIC  SIZE  TO"}:text{"AVOID COMBAT AND"}
     text{"GAIN  ACCESS  TO"}:text{"REMOTE  AREAS OF"}
     text{"THE   WHEEL   OF"}:text{"THINGS, EVEN THE"}
     text{"INSIDE   OF  THE"}:text{"HUMAN BODY......"}:clrom{11}
    Case 3
     text{"THE ICE BODY CAN"}:text{"GENERATE INTENSE"}
     text{"COLD,  EITHER AS"}:text{"PROJECTILES   OR"}
     text{"ICE PLATFORMS..."}:text{"IT IS TOUGH, BUT"}
     text{"A  BIT  SLOW  AT"}:text{"TIMES..........."}:clrom{11}
    Case 4
     text{"THE   INDUSTRIAL"}:text{"BODY IS BIG,SLOW"}
     text{"AND  VERY TOUGH."}:text{"IDEAL FOR COMBAT"}
     text{"AND BLASTING BIG"}:text{"HOLES IN THINGS!"}:clrom{11}
   End Select
  Case 2
   text{" THE INHABITANTS"}:text{"OF EARTH COME IN"}
   text{"4 TYPES,VIOLENT,"}:text{"NORMAL,OR SPACED"}
   text{"OUT ON VOID,   A"}:text{"VOLATILE DRUG..."}
   text{"AND GURUS.,,,,,,"}:text{" WHEN THEY SLEEP"}
   text{"THEY DREAM,  AND"}:text{"YOU  CAN   ENTER"}
   text{"THEIR DREAMS.THE"}:text{"DREAMERS CAN  BE"}
   text{"FOUND, AND TAKEN"}:text{"WITH  YOU  ALONG"}
   text{"THE DREAM TRACK,"}:text{"CHANGING AS THEY"}
   text{"GO,  IF  YOU USE"}:text{"THE ATOMIC BODYS"}
   text{"DREAM DUST......"}:text{"DREAMS ALSO LEAD"}
   text{"FROM EARTH,   TO"}:text{"THE  OUTER EDGES"}
   text{"OF  THE WHEEL OF"}:text{"THINGS          "}
   clrom{11}
  Case 3
   text{"    THE WHEEL OF"}:text{"THINGS    INDEED"}
   text{"CONTAINS    MANY"}:text{"THINGS,   WORLDS"}
   text{"WHICH GOVERN THE"}:text{"LAWS OF PHYSICS,"}
   text{"THE WEATHER, AND"}:text{"OTHER ASPECTS OF"}
   text{"LIFE ON EARTH.  "}:text{"THE FULL MR. ZEN"}
   text{"GAME  WILL  HAVE"}:text{"25 LEVELS,  FROM"}
   text{"NORMAL  PLATFORM"}:text{"ACTION,?????TWISTED"}
   text{"VARIATIONS    ON"}:text{"THIS  THEME   TO"}
   text{"HIGH SPEED SHOOT"}:text{"EM UPS.........."}
   text{".....AS THE MANY"}:text{"BODIED  ELECTRIC"}
   text{"BUDDHA  HIMSELF,"}:text{"YOU  MUST  GUIDE"}
   text{"THE   POPULATION"}:text{"OF THE EARTH  TO"}
   text{"ENLIGHTENMENT,BY"}:text{"ALTERING   THEIR"}
   text{"ENVIRONMENT ON A"}:text{"GRAND SCALE,  OR"}
   text{"BY DIRECT ACTION"}:text{"WITH A  SPECIFIC"}
   text{"INDIVIDUAL......"}
   clrom{11}
 End Select
Until zenu=4
For k=0 To 1:db=1-db
Use BitMap db:UnBuffer db:Boxf 80,48,207,143,7:Block 167,128,128:Block 183,144,128:BBlit db,za0,144,128
VWait:ShowF db,16,16,bx:Next
Use BitMap 2
For i=0 To 15:For j=0 To 11
adr=1024+i+j ASL 4
Poke.b &n+1,Peek.b(&maparray(adr))
Block n,i ASL 4,j ASL 4:Block n,(i+16) ASL 4,j ASL 4
Block n,i ASL 4,(j+12) ASL 4:Block n,(i+16) ASL 4,(j+12) ASL 4
Next:Next
zv=-8:air=1
Return

CNIF #devo=0
.hel
For i=384 To 387:CopyShape i,i+4:XFlip i+4:Next
For i=392 To 395:CopyShape i,i+4:XFlip i+4:Next
ga0(1)=392:ghits(1)=3:gspd(1)=3:gtile(1)=0:gbul(1)=4;fish right
ga0(3)=396:ghits(3)=3:gspd(3)=3:gtile(3)=0:gbul(3)=3;fish left
ga0(4)=384:ghits(4)=3:gspd(4)=2:gtile(4)=0:gbul(4)=0;walker
ga0(5)=384:ghits(5)=3:gspd(5)=2:gtile(5)=0:gbul(5)=1;walker w/bullets
ga0(8)=376:ghits(8)=2:gspd(8)=4:gtile(8)=0:gbul(8)=0;ribs
ga0(9)=376:ghits(9)=2:gspd(9)=4:gtile(9)=0:gbul(9)=1;ribs w/ bullets
ga0(10)=372:ghits(10)=1:gspd(10)=3:gtile(10)=0:gbul(10)=0;meat blob
ga0(11)=412:ghits(11)=3:gspd(11)=3:gtile(11)=0:gbul(11)=1;insect
ga0(16)=400:gspd(16)=6:sbul=15;bullet
ga0(18)=407:ga0(19)=411;explosions
For i=0 To 15:CopyShape gtile(i),i*16+4:Next:gun(5)=16
For i=0 To 8:CopyShape 416+i,425+i:XFlip 425+i
Handle 425+i,ShapeWidth(416+i)-Peek.w(Addr Shape(416+i)+10),Peek.w(Addr Shape(416+i)+12):Next
Data.w 10;switches
Data.w 31,28,12,35,1,1:Data.b 8
Data.w 62,4,42,39,3,3:Data.b 0,0,43,0,43,176,43,176,0
Data.w 55,11,19,12,1,1:Data.b 86
Data.w 17,10,45,56,1,2:Data.b 16,16
Data.w 63,35,18,8,1,4:Data.b 0,0,0,0
Data.w 78,5,78,120,2,2:Data.b 123,124,115,131
Data.w 65,68,8,76,3,2:Data.b 72,72,23,161,161,145
Data.w 24,70,20,77,2,1:Data.b 8,24
Data.w 120,56,41,121,3,1:Data.b 40,56,13
Data.w 18,120,21,99,1,2:Data.b 147,163
Data.w 127,2,113,24,15,1:Data.b 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
Data.w 2;doors
Data.w 344,1608,16,51,5
;Data.w 15566,0,0,10
;Data.w 15567,0,0,10
Data.w 1032,1936,62,58,4
Data.w 1000,912,64,122,4
bmc(1)=$446:bmc(2)=$88a:bmc(3)=$620
monadr=-1:dtype=0:fa0=416
If olev<>4
 rand.w=Rnd(4):Select rand
 Case 0:fmx=30:fmy=4:Case 1:fmx=56:fmy=46:Case 2:fmx=75:fmy=78:Case 3:fmx=38:fmy=68:Default:fmx=125:fmy=77
 End Select
 fmx ASL 4:fmy ASL 4
EndIf
If friend>0 Then fmx=nzcx ASL 4:fmy=nzcy ASL 4
Gosub veil_of_maya:m$="FIND THE HEART OF THE DREAM...........................WATCH OUT FOR THE HEATED VATS!"
Repeat
If pips(0)>0 AND pips(1)>0
Select spell
Case 0
If icadr<>-1 Then m0.b=maparray(icadr):maparray(icadr)=6
If air=1 Then Gosub air Else Gosub ground
If icadr<>-1 Then maparray(icadr)=m0
tgx=zmx:tgy=zmy
Case 1:Gosub teleport
Case 2:Gosub shrink
End Select
Use BitMap 7:UnQueue 0:QBlit 0,za,48,64
If spell=0 Then Gosub zmagic
If maparray(zadr)=30 Then Gosub strike:sfx(0)=3
EndIf
Gosub fxevo
Gosub generic
Gosub friend
Gosub display
Gosub gcoll
Until gate<>0
Return

.rem
Data.w -1,1
Data.w 256,760,21,101,4
Data.w 64,56,50,63,6
Data.w 0,6,12,19,25,31,36,41,45,49,53,57,59,61,62,64
For i=0 To 3:gtile(i)=i ASL 4:Next
bmc(1)=$262:bmc(2)=$e40:bmc(3)=$a2c
monadr=-1:bu0=326:dam=64:dtype=1:fa0=400
fmx=Rnd(384)+64:fmy=Rnd(768)+64:fuu=2
If friend>0 Then fmx=nzcx ASL 4:fmy=nzcy ASL 4
zvv=0:zvu=-2:If zdir=298 Then zvu=2:za=298
For i=0 To 2:CopyShape i+303,i+298:xh(i)=Peek.w(Addr Shape(298+i)+10):yh(i)=Peek.w(Addr Shape(298+i)+12):Next
For j=6 To 18 Step 6:For i=0 To 2:CopyShape 298+i,298+i+j:Next:Next
For j=0 To 18 Step 6
xmet.q=1.225:If j=6 OR j=18 Then xmet=0.816
ymet.q=1.225:If j>6 Then ymet= 0.816
For i=0 To 2
x0=ShapeWidth(298+i+j):Scale 298+i+j,xmet,ymet,0:Handle 298+i+j,xmet*xh(i),ymet*yh(i)
CopyShape 298+i+j,301+i+j:XFlip 301+i+j:Handle 301+j,xmet*(x0-xh(i)),ymet*yh(i)
Next
Next
For i=0 To 3
 For j=0 To 3
  sh=400+i*8+j
  xh=Peek.w(Addr Shape(sh)+10):yh=Peek.w(Addr Shape(sh)+12)
  CopyShape sh,sh+4:XFlip sh+4:Handle sh+4,ShapeWidth(i+4)-xh,yh
 Next
Next
For i=352 To 355:CopyShape i,i-30:Next
For i=342 To 345:CopyShape i,i-16:Next
For i=385 To 387:CopyShape 384,i:Next
For i=389 To 391:CopyShape 388,i:Next
Scale 385,0.67,1.0,0:Scale 386,1.0,0.67,0:Scale 387,0.67,0.67,0
Scale 388,1.5,1.5,0:Scale 389,1.0,1.5,0:Scale 390,1.5,1.0,0
For i=385 To 390:MidHandle i:Next
zadr=nzcx+nzcy ASL fsh
monadr=-1:bodyflag=0
Gosub veil_of_maya:m$="W O W    TRIPPY!"
Restore rem
For i=0 To 11:Read sine(0):Next:For i=0 To 15:Read sine(i):Next
Repeat
If pips(0)>0 AND pips(1)>0
;platform
If Joyx(1)<>0 Then zvu=2*Joyx(1)
If zvu=-2 AND zcx(db)<1 Then zvu=2:sfx(0)=5
If zvu=2 AND zcx(db)>30 Then zvu=-2:sfx(0)=5
zvv+2
typ=maparray(zadr) ASR 4
If zvv>0
 If zcy(db)>55 OR typ>3 Then zvv=-QLimit(zvv+8,16,32):sfx(0)=5
Else
 If Joyy(1)=1 Then zvv+2
 If zcy(db)<2 Then zvv=0
EndIf
typ&3:zu=zvu*(2+typ&1)
vt=6:If typ>1 Then vt=8
zv=QLimit(zvv,-vt,vt)
typ=(maparray(zadr-fw)ASR 4)&3
zv0=Sgn(zv):If Abs(zvv)<4 Then zv0=0
za=302-3*((1+Sgn(zu))ASR 1)+zv0+6*typ
Use BitMap 7:UnQueue 0:QBlit 0,za,48,64
EndIf
;doors
If zadr=3023 OR zadr=3024 Then gate=1:nzcx=21:nzcy=101:nlev=4
If zadr=195 OR zadr=196 Then gate=1:nzcx=50:nzcy=63:nlev=6
;zmagic/fxevo
If magic>0 OR Joyb(1)<>0 Then magic+1
If magic=1
 If AddItem(eb())
 USEPATH eb()
 sfx(0)=sfx2
 \x=zcx(db) ASL 4 +zx(db)
 \y=zcy(db) ASL 4 +zy(db)+8-ShapeHeight(za)
 \id=1
 \u=Joyx(1)*eu:\v=Joyy(1)*ev+ev0
 If Joyx(1)=0 AND Joyy(1)=0 Then \u=eu*Sgn(zu)
 \z=18
 \a=322
 EndIf
EndIf
If magic>=5 AND Joyb(1)=0 Then magic=0
zmx=zcx(db) ASL 4:zmy=zcy(db) ASL 4
nzx=zx(db)+zu:nzy=zy(db)+zv
zmx+nzx:zmx&$fff0:zmy+nzy:zmy&$fff0
ResetList eb()
While NextItem(eb())
USEPATH eb()
Select \id
Case 1;blast
 adr=QLimit(\x ASR 4+(\y ASR 4) ASL fsh,0,4095)
 typ=(maparray(adr) ASR 4)&3
 \v+egrav:v0=\v:If typ&2=0 Then v0*3:v0 ASR 1
 u0=\u:If typ&1=0 Then u0*3:u0 ASR 1
 \z-1
 \x=QWrap(\x+u0,0,fw ASL 4):\y=QWrap(\y+v0,0,fh ASL 4)
 If \x<0 OR \x>511 OR \y<0 OR \y>895 Then \id=0:\a=364:\z=4:noise{1,7,Abs(\x-zmx)+Abs(\y-zmy)}
 \a=322+(\z&3)
Case 0;explosion
 \z-1:\a=375-\z
End Select
If \z=-1 Then KillItem eb()
Wend
;monsters
nbob=-1
ResetList alien()
USEPATH alien()
If monadr<>-1
 If AddItem(alien())
 \x=(monadr&fwm1) ASL 4:\y=(monadr ASR fsh)ASL 4
 \u=0:\v=0:\hm=monadr:Poke.b &n+1,Peek.b(&maparray(monadr))
 \id=n/16:maparray(monadr)=gtile(\id)
 EndIf
EndIf
While NextItem(alien())
xflag=1:typ=\id:If typ=2 Then typ=1
Select typ
Case 0;headspin
 If \u=0 AND \v=0 Then \u=\x:\v=\y:If \v>zmy Then \z=32
 \z+1:\z&63
 If \z<16 Then \x=\u+sine(\z):\y=\v-sine(15-\z)
 If \z>15 AND \z<32 Then \x=\u+sine(31-\z):\y=\v+sine(\z-16)
 If \z>31 AND \z<48 Then \x=\u-sine(\z-32):\y=\v+sine(47-\z)
 If \z>47 Then \x=\u-sine(63-\z):\y=\v-sine(\z-48)
 \a=376+\z&7
 If \z&7=0 Then noise{3,16,Abs(\x-zmx)+Abs(\y-zmy)}
Case 1;stereo
 \v+2:If \v>8 Then \v=8
 x0=\x ASR 4:y0=\y ASR 4:adr=QLimit(x0+y0 ASL 6,0,4095)
 t0=maparray(adr) ASR 4
 u0=\u:If t0&2=0 Then u0*3:u0 ASR 1
 v0=\v:If t0&1=0 Then v0*3:v0 ASR 1
 \x+u0:\y+v0
 If \v>=8 AND t0>=4 Then \v=-6:\u=4*Sgn(Rnd(2)-1)
 \a=384+t0&3
 If \y>895 Then \id=18:\z=4
Case 3;mask
 x0=\x ASR 4:y0=\y ASR 4:adr=QLimit(x0+y0 ASL 6,0,4095)
 t0=maparray(adr) ASR 4
 u0=\u:If t0&2=0 Then u0*3:u0 ASR 1
 v0=\v:If t0&1=0 Then v0*3:v0 ASR 1
 \x+u0:\y+v0
 \a=388+t0&3
 If (cyc+\z)&7=0
  dx=\x-zmx:dy=\y-zmy+16
  dist=Abs(dx)+Abs(dy):\u=-4*dx/dist:\v=-4*dy/dist
 EndIf
Case 16;bullet
 x0=\x ASR 4:y0=\y ASR 4:adr=QLimit(x0+y0 ASL 6,0,4095)
 t0=maparray(adr) ASR 4
 u0=\u:If t0&2=0 Then u0*3:u0 ASR 1
 v0=\v:If t0&1=0 Then v0*3:v0 ASR 1
 \x+u0:\y+v0
 \a=392+cyc&3
 If \x<0 OR \x>511 OR \y<0 OR \y>895 Then \id=19:\z=4:noise{2,8,Abs(\x-zmx)+Abs(\y-zmy)}
Case 18;explosion
 \z-1:\a=375-\z
 If \z=-1 Then KillItem alien():xflag=0
Case 19;bullet explosion
 \z-1:\a=399-\z
 If \z=-1 Then KillItem alien():xflag=0
End Select
If xflag<>0
scrx=144+\x-zmx
scry=128+\y-zmy
Use BitMap 6:p=Point(6+scrx/16,6+scry/16)
If p=1
 nbob+1:xbob(nbob)=scrx:ybob(nbob)=scry:abob(nbob)=\a
  If \id=3 AND cyc&7=3
   x0=\x:y0=\y
   If AddItem(alien())
    noise{2,9,Abs(\x-zmx)+Abs(\y-zmy)}
    \x=x0:\y=y0
    \id=16:dx=\x-zmx:dy=\y-zmy+16
    dist=Abs(dx)+Abs(dy):\u=-8*dx/dist:\v=-8*dy/dist
   EndIf
  EndIf
 EndIf
 If p<1
  xflag=0
  If \id<16 Then maparray(\hm)=4+\id ASL 4
  KillItem alien()
 EndIf
EndIf
Wend
fscrx=144+QWrap(fmx-zmx,-xlim,xlim)
fscry=128+QWrap(fmy-zmy,-ylim,ylim)
fcyc=cyc&3
adr=(fmx ASR 4)+(fmy ASR 4) ASL fsh
If friend>0;hypnotised
 If cyc&3=0 Then noise{3,2,Abs(\x-zmx)+Abs(\y-zmy)}
 fa=fa0+3:If fscrx>144 Then fa+4
 If fscrx<112 Then fmx+1:If fscrx<96 Then fmx+2
 If fscrx>176 Then fmx-1:If fscrx>192 Then fmx-2
 If fscry<96 Then fmy+1:If fscry<80 Then fmy+2
 If fscry>160 Then fmy-1:If fscry>176 Then fmy-2
Else
 fvv+2:fmx+fuu:fvvv=QLimit(fvv,-8,8)
 If (maparray(adr)ASR 4)&1=0 Then fmx+fuu
 If (maparray(adr)ASR 4)&2=2 Then fvvv=QLimit(fvvv,-6,6)
 fmy+fvvv
 If fvv>0
  If maparray(adr)>63 OR fmy>888 Then fvv=-4-fvv:If Rnd(32)<16 Then fuu=-fuu
 EndIf
 If fmy<8 Then fvv=0
 If fmx<8 Then fuu=2
 If fmx>488 Then fuu=-2
 fa=fa0+Sgn(fvv)+1:If Abs(fvv)<8 Then fa=fa0+2
 If fuu<2 Then fa+4
EndIf
fa+((maparray(adr)ASR 4)&3)ASL 3
If fscrx>=16 AND fscrx<=288 AND fscry>=32 AND fscry<=240;on screen
 nbob+1:xbob(nbob)=fscrx:ybob(nbob)=fscry:abob(nbob)=fa
 If friend>0
  nbob+1:xbob(nbob)=fscrx:ybob(nbob)=fscry-16:abob(nbob)=bu0+fcyc
 Else
  If body=2
   ResetList eb()
   USEPATH eb()
   While NextItem(eb())
    If ShapesHit(\a,\x,\y,fa,fmx,fmy) Then friend=1
   Wend
  EndIf
 EndIf
EndIf
Gosub display
Gosub gcoll
Until gate<>0
Return

.anx
Data.w 2;switches
Data.w 22,45,52,23,2,2:Data.b 16,16,16,16
Data.w 34,60,54,21,1,2:Data.b 16,16
Data.w 13,20,54,61,1,2:Data.b 16,16
Data.w 1;doors
Data.w 504,976,4,4,5
;Data.w 2965,0,0,10
Data.w 232,336,10,255,7
For i=0 To 3:CopyShape i+376,i+380:XFlip i+380:Next
For i=0 To 3:CopyShape i+384,i+388:XFlip i+384
Handle i+384,ShapeWidth(i+388)-Peek.w(Addr Shape(i+388)+10),Peek.w(Addr Shape(i+388)+12):Next
For i=0 To 3:CopyShape i+392,i+396:Next
ga0(0)=372:ghits(0)=10:gtile(0)=55:gspd(0)=0:gbul(0)=2;computer
ga0(4)=376:ghits(4)=2:gtile(4)=16:gspd(4)=4:gbul(4)=0;tea lady
ga0(5)=384:ghits(5)=24:gtile(5)=16:gspd(5)=1:gbul(5)=1;boss
ga0(6)=392:ghits(6)=4:gtile(6)=16:gspd(6)=3:gbul(6)=0;spark
ga0(7)=392:ghits(7)=4:gtile(7)=16:gspd(7)=1:gbul(7)=2;spark
ga0(8)=400:ghits(8)=1:gtile(8)=0:gspd(8)=2:gbul(8)=1;eye
ga0(12)=408:ghits(12)=1:gtile(12)=80:gspd(12)=2:gbul(12)=0;pounds
ga0(14)=422:ghits(14)=12:gtile(14)=192:gspd(14)=4:gbul(14)=2;boss two
ga0(16)=418:gspd(16)=6:sbul=1
ga0(18)=413:ga0(19)=417:gun(0)=16
bmc(1)=$cce:bmc(2)=$aae:bmc(3)=$000
monadr=-1:dtype=0:fa0=423
For i=0 To 15:CopyShape gtile(i),i*16+4:Next
For i=0 To 8:CopyShape 423+i,432+i:XFlip 432+i
Handle 432+i,ShapeWidth(423+i)-Peek.w(Addr Shape(423+i)+10),Peek.w(Addr Shape(423+i)+12):Next
If olev<>6
 rand.w=Rnd(4):Select rand
 Case0:fmx=26:fmy=55:Case 1:fmx=61:fmy=43:Case 2:fmx=13:fmy=47:Case 3:fmx=44:fmy=31:Default:fmx=19:fmy=23
 End Select
 fmx ASL 4:fmy ASL 4
EndIf
If friend>0 Then fmx=nzcx ASL 4:fmy=nzcy ASL 4
Gosub veil_of_maya:m$="ITS 5 PAST 9.....WHERE ARE MY TROUSERS?......OH GOD,MY BOSS IS GOING TO KILL ME!"
Repeat
If pips(0)>0 AND pips(1)>0
Select spell
Case 0
If icadr<>-1 Then m0.b=maparray(icadr):maparray(icadr)=6
If air=1 Then Gosub air Else Gosub ground
If icadr<>-1 Then maparray(icadr)=m0
tgx=zmx:tgy=zmy
Case 1:Gosub teleport
Case 2:Gosub shrink
End Select
Use BitMap 7:UnQueue 0:QBlit 0,za,48,64
If spell=0 Then Gosub zmagic
EndIf
Gosub fxevo
Gosub generic
Gosub friend
Gosub display
Gosub gcoll
Until gate<>0
Return

.cjk
Data.w 7;switches
Data.w 5,209,19,229,1,1:Data.b 24
Data.w 18,187,21,200,1,4:Data.b 8,0,0,8
Data.w 31,165,4,173,3,1:Data.b 0,0,0
Data.w 12,178,6,197,1,2:Data.b 3,19
Data.w 8,146,2,105,4,1:Data.b 40,40,40,40
Data.w 28,34,3,145,1,2:Data.b 3,19
Data.w 14,80,31,56,1,6:Data.b 178,38,17,17,178,38
Data.w 9,68,2,48,3,2:Data.b 0,0,0,109,0,0
Data.w 2;doors
Data.w 104,3176,15,155,7
Data.w 248,2472,6,199,7
Data.w 0,4008,14,23,6
If olev<>7
 For j=0 To 24 Step 8
 For i=j+380 To j+383:CopyShape i,i+4:XFlip i:Next
 Next
EndIf
CopyShape 414,427:XFlip 427:fa0=428
For i=0 To 8:CopyShape 428+i,437+i:XFlip 437+i
Handle 437+i,ShapeWidth(428+i)-Peek.w(Addr Shape(428+i)+10),Peek.w(Addr Shape(428+i)+12):Next
ga0(0)=372:ghits(0)=4:gtile(0)=82:gspd(0)=3:gbul(0)=0;shark infested custard
ga0(1)=376:ghits(1)=2:gtile(1)=0:gspd(1)=0:gbul(1)=2;electric buzzer
ga0(4)=380:ghits(4)=3:gtile(4)=0:gspd(4)=2:gbul(4)=0;my dog
ga0(5)=388:ghits(5)=10:gtile(5)=166:gspd(5)=1:gbul(5)=0;englishman
ga0(6)=396:ghits(6)=5:gtile(6)=0:gspd(6)=4:gbul(6)=0;irishman
ga0(7)=404:ghits(7)=8:gtile(7)=0:gspd(7)=2:gbul(7)=0;scotsman
ga0(12)=412:ghits(12)=1:gtile(12)=0:gspd(12)=4:gbul(12)=0;watches
ga0(14)=414:ghits(14)=4:gtile(14)=0:gspd(14)=4:gbul(14)=3;sir!
ga0(15)=427:ghits(15)=4:gtile(15)=0:gspd(15)=4:gbul(15)=4;sir! (left facing)
ga0(16)=415:gspd(16)=8:sbul=7;bullets
ga0(18)=422:ga0(19)=426;explosion
For i=0 To 15:CopyShape gtile(i),i*16+4:Next
bmc(1)=$ee6:bmc(2)=$6a2:bmc(3)=$060
monadr=-1:dtype=0
If olev<>7
 rand.w=Rnd(4):Select rand
 Case0:fmx=4:fmy=210:Case 1:fmx=3:fmy=155:Case 2:fmx=28:fmy=89:Case 3:fmx=6:fmy=48:Default:fmx=29:fmy=17
 End Select
 fmx ASL 4:fmy ASL 4
EndIf
If friend>0 Then fmx=nzcx ASL 4:fmy=nzcy ASL 4
Gosub veil_of_maya:m$="MY DOGS GOT NO NOSE!.........................WHATS YELLOW AND DANGEROUS?.................."
Repeat
If pips(0)>0 AND pips(1)>0
Select spell
Case 0
If icadr<>-1 Then m0.b=maparray(icadr):maparray(icadr)=6
If air=1 Then Gosub air Else Gosub ground
If icadr<>-1 Then maparray(icadr)=m0
tgx=zmx:tgy=zmy
Case 1:Gosub teleport
Case 2:Gosub shrink
Case 3:If dimflag=0 AND Len(m$)=0 Then lives=0:gate=-1
End Select
Use BitMap 7:UnQueue 0:QBlit 0,za,48,64
If spell=0 Then Gosub zmagic
EndIf
Gosub fxevo
Gosub generic
Gosub friend
Gosub display
Gosub gcoll
If zadr=269 AND spell<>3
 spell=3
 m$="WELL DONE, KARMANAUT! YOU HAVE SUCCESSFULLY TRAVELLED THE FULL LENGTH OF THE DREAM TRACK!"
 m$+"..............ON THE WAY YOU MAY HAVE NOTICED A FEW DEFUNCT DOORWAYS, THESE WILL TAKE YOU"
 m$+" TO THE FUNDAMENTAL LIBRARY AND INFORMATION ZERO, AND FROM THERE OUT INTO THE WHEEL OF"
 m$+" THINGS! THE FULL MR.ZEN SHOULD BE READY BY THE END OF 1994, WATCH THIS SPACE!"
EndIf
Until gate<>0
Return
CEND

.display
db=1-db:cyc+1:monadr=-1
Use BitMap db
zzu=zu+ozu:zzv=zv+ozv
nzx=zx(db)+zzu:nzy=zy(db)+zzv
hscr=0:vscr=0

UnBuffer db:UnBuffer 2+db

If zzu>0
 If nzx>15 Then hscr=1:nzx-16
 If ne(db)=0
  a0=((zcx(db)+9)&fwm1)+QWrap(zcy(db)-8,0,fh) ASL fsh:y0=0
  For i=0 To 14
   Poke.b &n+1,Peek.b(&maparray(a0))
   Block n,288,y0:If n&15=4 Then monadr=a0
   y0+16:a0+fw:If a0>=size Then a0-size
  Next
  ne(db)=-1
 EndIf
EndIf

If zzu<0
 If nzx<0 Then hscr=-1:nzx+16
 If nw(db)=0
  a0=((zcx(db)-9)&fwm1)+QWrap(zcy(db)-8,0,fh) ASL fsh:y0=0
  For i=0 To 14
   Poke.b &n+1,Peek.b(&maparray(a0))
   Block n,0,y0:If n&15=4 Then monadr=a0
   y0+16:a0+fw:If a0>=size Then a0-size
  Next
  nw(db)=-1
 EndIf
EndIf

If zzv<0
 If nzy<0 Then vscr=-1:nzy+16
 If nn(db)=0
  a0=((zcx(db)-9)&fwm1)+QWrap(zcy(db)-8,0,fh) ASL fsh:x0=0
  For i=0 To 18
   Poke.b &n+1,Peek.b(&maparray(a0))
   Block n,x0,0:If n&15=4 Then monadr=a0
   x0+16:a0+1:If a0&fwm1=0 Then a0-fw:If a0<0 Then a0+size
  Next
  nn(db)=0
 EndIf
EndIf

If zzv>0
 If nzy>15 Then vscr=1:nzy-16
 If ns(db)=0
  a0=((zcx(db)-9)&fwm1)+QWrap(zcy(db)+6,0,fh) ASL fsh:x0=0
  For i=0 To 18
   Poke.b &n+1,Peek.b(&maparray(a0))
   Block n,x0,224:If n&15=4 Then monadr=a0
   x0+16:a0+1:If a0&fwm1=0 Then a0-fw:If a0<0 Then a0+size
  Next
  ns(db)=0
 EndIf
EndIf

situ=-hscr-3*vscr

Select situ
  Case -4
    Scroll 16,16,288,224,0,0
    nw(db)=-1:ne(db)=0:nn(db)=-1:ns(db)=0:zcx(db)+1:zcy(db)+1
  Case -3
    Scroll 0,16,304,224,0,0
    nn(db)=-1:ns(db)=0:zcy(db)+1
  Case -2
    Scroll 0,16,288,224,16,0
    nn(db)=-1:ns(db)=0:nw(db)=0:ne(db)=-1:zcx(db)-1:zcy(db)+1
  Case -1
    Scroll 16,0,288,240,0,0
    nw(db)=-1:ne(db)=0:zcx(db)+1
  Case 1
    Scroll 0,0,288,240,16,0
    nw(db)=0:ne(db)=-1:zcx(db)-1
  Case 2
    Scroll 16,0,288,224,0,16
    nw(db)=-1:ns(db)=-1:nn(db)=0:ne(db)=0:zcx(db)+1:zcy(db)-1
  Case 3
    Scroll 0,0,304,224,0,16
    ns(db)=-1:nn(db)=0:zcy(db)-1
  Case 4
    Scroll 0,0,288,224,16,16
    ne(db)=-1:nw(db)=0:nn(db)=0:ns(db)=-1:zcx(db)-1:zcy(db)-1
End Select

If swatch>0 Then swatch-1:Block swicon,144,112:Block 25-(swicon ASR 1),144,128

If nun>0
 nun-1
 scrx=144+QWrap(unx-zmx,-xlim,xlim)
 scry=128+QWrap(uny-zmy,-ylim,ylim)
 Use BitMap db:If scrx>=0 AND scrx<=288 AND scrx>=0 AND scry<=224 Then Block una,scrx,scry
EndIf

For i=0 To nbob
BBlit db+2,abob(i),xbob(i),ybob(i)
Next

If ntoken>0
 ttime-1:If ttime=0 Then ntoken=0:If pips(0)=0 OR pips(1)=0 Then gate=-1
 For i=0 To ntoken
  USEPATH token(i)
  \a=(\id*4)+286+(cyc+i)&3
  \v+2:\y+QLimit(\v,-8,8):\x+\u
  Use BitMap 6:p=Point(6+\x/16,6+\y/16)
  Use BitMap db:If p=1 Then BBlit db,\a,\x,\y
 Next
 VWait
EndIf

If pips(0)>0 AND pips(1)>0

 For i=0 To 1
  If hit(i)>0
   hit(i)-1
   If (cyc&1) Then BBlitMode SolidMode
  EndIf
 Next

 BBlit db,za,144+nzx,128+nzy
 BBlitMode CookieMode
 If magic>0 Then BBlit db,bu0+(magic&3),144+nzx,136+nzy-ShapeHeight(za)
EndIf

ResetList eb()
While NextItem(eb())
USEPATH eb()
scrx=144+QWrap(\x-zmx,-xlim,xlim)
scry=128+QWrap(\y-zmy,-ylim,ylim)
Use BitMap 6:p=Point(6+scrx/16,6+scry/16)
Use BitMap db:If p=1 Then BBlit db,\a,scrx,scry
Wend

Use Slice 1
bmx=(cyc&7)ASL 1
If bmx=0
 Use BitMap 4
 If Len(m$)>0
  If Len(m$)=1 Then m$=m$+"."
  BlitMode CookieMode
  Blit trans{Asc(Left$(m$,1))},256,4
  Blit trans{Asc(Mid$(m$,2,1))},264,4
  m$=Right$(m$,Len(m$)-2)
  If dimflag=0
   For i=1 To 3
    RGB i+8,bmc(i) ASR 9,(bmc(i)ASR 5)&15,(bmc(i)ASR 1)&15
   Next
   dimflag=1
  EndIf
 EndIf
 If Len(m$)=0 AND dimflag=1 Then dimt=16:dimflag=0
 If dimt>0
  dimt-1
  If dimt=0
   For i=1 To 3
    RGB i+8,bmc(i) ASR 8,(bmc(i)ASR 4)&15,bmc(i)&15
   Next
  EndIf
 EndIf
 Scroll 16,0,256,16,0,0
 Boxf 256,0,271,15,0
EndIf
ShowF 4,bmx,0,0:ShowB 3,0,0,bmx

Use Slice 0
bx=QWrap(bx+(zu ASR 1),0,bw ASL 4)
by=QWrap(by+(zv ASR 1),0,bh ASL 4)
VWait
ShowF db,16+nzx,16+nzy,bx
ShowB 2,bx,by,16+nzx
zcx(db)=QWrap(zcx(db),0,fw):zcy(db)=QWrap(zcy(db),0,fh)
zadr=zcx(db)+zcy(db) ASL fsh

zdx=zcx(db) ASL 4+nzx:zdy=zcy(db) ASL 4+nzy

drflag=0
For k=0 To ndoors
scrx=144+QWrap(drpx(k)-zdx,-xlim,xlim)
scry=128+QWrap(drpy(k)-zdy,-ylim,ylim)
Use BitMap 6:p=Point(6+scrx/16,6+scry/16)
If p=1 Then ShowSprite (9+cyc&7),16+scrx,scry-16,4:drflag=1
Next
If drflag=0 Then ShowSprite 0,264,24,4

ozu=zu:ozv=zv
zx(db)=nzx:zy(db)=nzy

If clock<2 Then VWait
clock=0

If sfx(0)<>0 Then Sound sfx(0),1,vsfx(0):sfx(0)=0
For i=1 To 3
vsfx(i)=QLimit(63-(vsfx(i) ASR 2),0,63)
If sfx(i)<>0 Then Sound sfx(i),(1 ASL i),vsfx(i):sfx(i)=0
vsfx(i)=400
Next
Return

.zmagic
If magic>0 OR Joyb(1)<>0 Then magic+1
If magic=1
 If AddItem(eb())
 USEPATH eb()
 \x=zcx(db) ASL 4 +zx(db)
 \y=zcy(db) ASL 4 +zy(db)+8-ShapeHeight(za)
 \id=1
 \u=Joyx(1)*eu:\v=Joyy(1)*ev+ev0
 If Joyx(1)=0 AND Joyy(1)=0 Then \u=eu:If zdir=320 Then \u=-eu
 If Joyx(1)=0 AND Joyy(1)=1 AND air=0 Then \v=ev0:\u=eu:If zdir=320 Then \u=-eu
 \z=18
 \a=352
 sfx(0)=sfx2
 EndIf
EndIf
If Joyb(1)=0 AND magic>=5 Then magic=0
If magic=sfxt
 magic=0
 If body=1 AND air=0 Then spell=1:tpt=16:If zu=0 AND zv=0 Then zv=-4:za=356
 If body=2 AND air=0 Then spell=2:zu=0:zv=0:za=356:sflag=1:sfx(0)=9
 If body=3 AND air=0
  If typ(maparray(zadr)&15)<>0
   AddItem eb()
   USEPATH eb()
   \x=(zcx(db)ASL4)+8:\y=(zcy(db)ASL4)
   \id=2:\z=35:\a=typ(maparray(zadr)&15)*5+350
   icadr=zadr-fw:If typ(maparray(zadr)&15)=1 Then icadr-fw:\y-16
   If icadr<0 Then icadr+size
   If \y<0 Then \y+(fh ASL 4)
  EndIf
 EndIf
EndIf
Return

.air
If maparray(zadr)&15=3 Then Gosub doors
If zu=0 Then zu=speed*Joyx(1):If Joyx(1)<>0 Then zdir=309-11*Joyx(1)
If zu<0 AND Joyx(1)=1 Then zu=-zu:zdir=298
If zu>0 AND Joyx(1)=-1 Then zu=-zu:zdir=320
If Joyy(1)=1 AND zv<=0 Then zvv+2
zvv=zvv+grav:zv=QLimit(zvv,-8,8)
za=zdir+6+Sgn(zv)
If zv<0
 adr=zadr-(fw ASL 1):If adr<0 Then adr+size
 n=maparray(adr)&15
 If n>12 Then zvv=0:zv=0:za=zdir+6
EndIf
If zv>0
 n1=maparray(zadr)&15
 Select n1
  Case 11
   zu=0:If zx(db)+zy(db)>=7 Then zv=15-zx(db)-zy(db):lflag=2:air=0:za=318:If zdir=320 Then za=338
  Case 12
   zu=0:If zx(db)-zy(db)<=8 Then zv=zx(db)-zy(db):lflag=2:air=0:za=316:If zdir=320 Then za=340
  Default
   adr=zadr+fw:If adr>=size Then adr-size
   n=maparray(adr)&15:t0=typ(n)
   If t0=1 AND zy(db)>=8 Then lflag=2:air=0:zvu=zu:zu=0:zv=16-zy(db):za=zdir+9
   If t0=2 AND zx(db)+zy(db)>=23 Then lflag=2:air=0:zu=0:zv=31-zx(db)-zy(db):za=318:If zdir=320 Then za=338
   If t0=2 AND zu>0 AND lflag=0 Then zu=0
   If t0=3 AND zx(db)-zy(db)<=-8 Then lflag=2:air=0:zu=0:zv=16+zx(db)-zy(db):za=316:If zdir=320 Then za=340
   If t0=3 AND zu<0 AND lflag=0 Then zu=0
 End Select
EndIf
If zu<0 AND zx(db)<8
 adr=zadr+Sgn(zv)*fw-1:If adr&fwm1=fwm1 Then adr+fw
 If adr<0 Then adr+size
 If adr>=size Then adr-size
 n=maparray(adr)&15:If n>12 Then zu=0
 adr=zadr-1:If adr&fwm1=fwm1 Then adr+fw
 If maparray(adr)&15>12 Then zu=0
EndIf
If zu>0 AND zx(db)>7
 adr=zadr+Sgn(zv)*fw+1:If adr&fwm1=0 Then adr-fw
 If adr<0 Then adr+size
 If adr>=size Then adr-size
 n=maparray(adr)&15:If n>12 Then zu=0
 adr=zadr+1:If adr&fwm1=0 Then adr-fw
 If maparray(adr)&15>12 Then zu=0
EndIf
Return

.ground
dflag=0
n=maparray(zadr)&15:t0=typ(n)*Joyx(1):t00=typ(n)
If Joyx(1)<>0 Then zdir=309-11*Joyx(1)
If Joyx(1)=0
 Select t00
  Case 1:za=zdir:zu=(zu/4)*2:zv=0:If zy(db)<>0 Then air=1
  Case 2:zu=0:zv=0:za=309:If zdir=320 Then za=336
  Case 3:zu=0:zv=0:za=314:If zdir=320 Then za=331
 End Select
EndIf
eflag=0
If lflag>0
 zu=0:zv=0:lflag-1:t0=0:eflag=1
 If lflag=1 Then za+1:sfx(0)=4:If n=9 AND zx(db)>3 AND zx(db)<12 Then Gosub switch
 If n=8 Then air=1:zv=-8:zu=zvu:zvv=-QLimit(zvv+4,12,32):lflag=0:sfx(0)=5
EndIf
If n=8
 adr=zadr+Sgn(zu)+fw:If zx(db)=0 Then adr+fw
 If zx(db)=fwm1 Then adr-fw
 If adr>=size Then adr-size
 If maparray(adr)&15>12 Then zu=0
EndIf
If eflag=1 Then Return
Select t0
Case -3
 If body=1 AND zu<>-acc Then sfx(0)=sfx1
 zu=-acc:zv=-acc:nzx=zx(db)+zu:za=(cyc&3)+332
 If cyc&3=0 AND body<>1 Then sfx(0)=sfx1
 If nzx<0
  adr=zadr-1:If adr&fwm1=fwm1 Then adr+fw
  n=maparray(adr)&15:t0=typ(n)
  If t0=1 Then zv=-zy(db)
  If t0=2 Then nzy=15-(nzx&15):zv=nzy-zy(db)
 EndIf
Case -2
 If zu<>-8 Then sfx(0)=3
 zu=-8:zv=8:nzx=zx(db)-8:za=337
 If nzx<0
  adr=zadr-1+fw:If adr&fwm1=fwm1 Then adr+fw
  If adr>=size Then adr-size
  n=maparray(adr)&15:t0=typ(n)
  If t0=1 Then zv=16-zy(db)
  If n=5 Then nzy=nzx&15:zv=16+nzy-zy(db)
 EndIf
Case -1
 If body=1 AND zu=0 Then sfx(0)=sfx1
 zu=zu-2:If zu<-speed Then zu=-speed
 zv=0:nzx=zx(db)+zu:za=(cyc&3)+321
 If cyc&3=0 AND body<>1 Then sfx(0)=sfx1
 If nzx<0
  adr=zadr-1:If adr&fwm1=fwm1 Then adr+fw
  n=maparray(adr)&15
  If n=5 Then nzy=nzx&15:zv=nzy-16
  If n=11 Then nzy=15-(nzx&15):zv=nzy
 EndIf
Case 1
 If body=1 AND zu=0 Then sfx(0)=sfx1
 zu=zu+2:If zu>speed Then zu=speed
 zv=0:nzx=zx(db)+zu:za=(cyc&3)+299
 If cyc&3=0 AND body<>1 Then sfx(0)=sfx1
 If nzx>15
  adr=zadr+1:If adr&fwm1=0 Then adr-fw
  n=maparray(adr)&15
  If n=5 Then nzy=15-(nzx&15):zv=nzy-16
  If n=12 Then nzy=nzx&15:zv=nzy
 EndIf
Case 3
 If zu<>8 Then sfx(0)=3
 zu=8:zv=8:nzx=zx(db)+8:za=315
 If nzx>15
  adr=zadr+1+fw:If adr&fwm1=0 Then adr-fw
  If adr>=size Then adr-size
  n=maparray(adr)&15:t0=typ(n)
  If t0=1 Then zv=16-zy(db)
  If n=5 Then nzy=15-(nzx&15):zv=16+nzy-zy(db)
 EndIf
Case 2
 If body=1 AND zu<>acc Then sfx(0)=sfx1
 zu=acc:zv=-acc:nzx=zx(db)+zu:za=(cyc&3)+310
 If cyc&3=0 AND body<>1 Then sfx(0)=sfx1
 If nzx>15
  adr=zadr+1:If adr&fwm1=0 Then adr-fw
  n=maparray(adr)&15:t0=typ(n)
  If t0=1 Then zv=-zy(db)
  If t0=3 Then nzy=nzx&15:zv=nzy-zy(db)
 EndIf
End Select
If Joyy(1)=-1 AND Joyb(1)=0 Then air=1:zvv=zjv+zv:zv=-8:za=zdir+5
If t00=0 Then air=1:za=zdir+6
If t00=1 AND Joyy(1)=1 Then zu=0:zv=0:za=zdir+8
If zv<0
 adr=zadr-(fw ASL 1):If adr<0 Then adr+size
 If maparray(adr)&15>12 Then zu=0:zv=0
EndIf
If zu>0 AND zx(db)>7
 adr=zadr-fw+1:If adr&fwm1=0 Then adr-fw
 If adr<0 Then adr+size
 If maparray(adr)&15>12 Then zu=0:zv=0
EndIf
If zu<0 AND zx(db)<8
 adr=zadr-fw-1:If adr&fwm1=fwm1 Then adr+fw
 If adr<0 Then adr+size
 If maparray(adr)&15>12 Then zu=0:zv=0
EndIf
Return

.fxevo
zmx=zcx(db) ASL 4:zmy=zcy(db) ASL 4
nzx=zx(db)+zu:nzy=zy(db)+zv
zmx+nzx:zmx&$fff0:zmy+nzy:zmy&$fff0
ResetList eb()
While NextItem(eb())
USEPATH eb()
Select \id
Case 1;blast
 \v+egrav
 \z-1
 \x=QWrap(\x+\u,0,fw ASL 4):\y=QWrap(\y+\v,0,fh ASL 4)
 adr=(\x ASR 4)+(\y ASR 4)ASL fsh
 If maparray(adr)&15>12
  noise{1,sfx3,Abs(\x-zmx)+Abs(\y-zmy)}
  \id=0:\a=346:\z=6
  If body=4 AND maparray(adr)&15=14 Then una=gun(maparray(adr) ASR 4):maparray(adr)=una:nun=2:unx=\x&$fff0:uny=\y&$fff0
  If nun=2 Then noise{1,6,Abs(\x-zmx)+Abs(\y-zmy)}
 EndIf
 \a=352+(\z&3)
Case 0;explosion
 \z-1:\a=351-\z
Case 2;ice block
 \z-1
 If \z>29 Then \a+1:If \z&1=1 Then noise{1,14,Abs(\x-zmx)+Abs(\y-zmy)}
 If \z<4  Then \a-1:icadr=-1
End Select
If \z=-1 Then KillItem eb()
Wend
Return

.teleport
tpt-1:If tpt<0 Then spell=0:air=1
If tpt&3=3 Then sfx(0)=10
If Joyr(1)=8
 zu=QLimit(zu+2*Sgn(zu),-8,8)
 zv=QLimit(zv+2*Sgn(zv),-8,8)
Else
 zu=QLimit(zu+4*Joyx(1),-8,8)
 zv=QLimit(zv+4*Joyy(1),-8,8)
EndIf
za&$fffe
If Joyr(1)<>8 Then za=356+Joyr(1) ASL 1
If cyc&1 Then za|1
If zu>0
 adr=zadr+1:If adr&fwm1=0 Then adr-fw
 If maparray(adr)&15>12 Then zu=-Abs(zu)
EndIf
If zu<0
 adr=zadr-1:If adr&fwm1=fwm1 Then adr+fw
 If maparray(adr)&15>12 Then zu=Abs(zu)
EndIf
If zv<0
 adr=zadr-fw-fw:If adr<0 Then adr+size
 If maparray(adr)&15>12 Then zv=Abs(zv)
EndIf
If zv>=0
 If maparray(zadr)&15>12 Then zv=-QLimit(Abs(zv),2,8)
EndIf
Return

.shrink
If za<>361
 If Joyb(1)=1 OR sflag=1 Then za+1:zu=0:zv=0
 If Joyb(1)=0 AND air=0 Then za-1:zu=0:zv=0:If za=356 Then spell=0:sfx(0)=9
 sflag=0
Else
 Select Joyx(1)
  Case -1
  zu=0
  adr=zadr-1:If zadr&fwm1=fwm1 Then zadr+fw
  If maparray(zadr)&15=11 Then adr+fw:If adr>=size Then adr-size
  If air=0 Then adr-fw:If adr<0 Then adr+size
  If maparray(adr)&15<13 OR zx(db)>2 Then zu=-2
  Case 1
  zu=0
  adr=zadr+1:If zadr&fwm1=0 Then zadr-fw
  If maparray(zadr)&15=12 Then adr+fw:If adr>=size Then adr-size
  If air=0 Then adr-fw:If adr<0 Then adr+size
  If maparray(adr)&15<13 OR zx(db)<13 Then zu=2
  Case 0:zu=0
 End Select
 Select typ(maparray(zadr)&15)
  Case 0:air=1:zv=2
  Case 1
  If zy(db)<2 Then air=0:zv=0
  If zy(db)>1 Then air=1:zv=2
  Case 2
  If air=0 Then zv=-zu
  diag=zx(db)+zy(db)
  If air=1 AND diag=14 Then zv=1:zu=0:air=0
  If air=1 AND diag=15 Then zv=0:zu=0:air=0
  Case 3
  If air=0 Then zv=zu
  diag=zx(db)-zy(db)
  If air=1 AND diag=1 Then zv=1:zu=0:air=0
  If air=1 AND diag=0 Then zv=0:zu=0:air=0
 End Select
 adr=zadr-(fw ASL 1):If adr<0 Then adr+size
 adr2=zadr-fw:If adr2<0 Then adr2+size
 If Joyb(1)=0 AND air=0 AND maparray(adr)&15<13 AND maparray(adr2)&15<13 Then za=360
EndIf
Return

.doors
For k=0 To ndoors
If RectsHit(zdx,zdy,1,1,drpx(k)-8,drpy(k)-8,16,16) Then gate=k+1:nzcx=drx(k):nzcy=dry(k):nlev=drlev(k)
Next
Return

.switch
For k=0 To nswitch
If zadr=swpos(k)
 src=0
 For j=0 To swh(k)-1:For i=0 To sww(k)-1
  adr=swadr(k)+i+j*fw:Exchange maparray(adr),swinf(k,src):src+1
 Next:Next
 swatch=2:swicon=-32*(swset(lev) BitTst k):maparray(zadr-fw)=swicon:maparray(zadr)=25-(swicon ASR 1)
 swset(lev) BitChg k
 sfx(0)=14
EndIf
Next
Return

.friend
snflag=0
fmx=QWrap(fmx,-xlim,xlim):fmy=QWrap(fmy,-ylim,ylim)
fscrx=144+QWrap(fmx-zmx,-xlim,xlim)
fscry=128+QWrap(fmy-zmy,-ylim,ylim)
fcyc=cyc&3
If friend>0;hypnotised
 If cyc&3=0 Then dist=Abs(fscrx-144)+Abs(fscry-128):dist ASL 2:noise{3,2,dist}
 fa=fa0+8:If fscrx>144 Then fa=fa0+17
 If fscrx<112 Then fmx+1:If fscrx<96 Then fmx+2
 If fscrx>176 Then fmx-1:If fscrx>192 Then fmx-2
 If fscry<88 Then fmy+1:If fscry<72 Then fmy+2
 If fscry>152 Then fmy-1:If fscry>168 Then fmy-2
Else
 adr=(fmx ASR 4)+(fmy ASR 4)ASL fsh
 Select fact
  Case 0;walk right
   adr1=adr+1:If adr1&fwm1=0 Then adr1-fw
   If Rnd(32)=0 Then fact=2
   fmx+2:If fcyc=3 Then noise{3,1,Abs(fscrx-144)+Abs(fscry-128)}:If (maparray(adr1)&15=0 OR maparray(adr1-fw)&15>12) Then fact=2
   fa=fa0+fcyc
  Case 1;walk left
   adr-1:If adr&fwm1=fwm1 Then adr+fw
   If Rnd(32)=0 Then fact=3
   fmx-2:If fcyc=3 Then noise{3,1,Abs(fscrx-144)+Abs(fscry-128)}:If (maparray(adr)&15=0 OR maparray(adr-fw)&15>12) Then fact=3
   fa=fa0+9+fcyc
  Case 2;turn right to left
   fa=fa0+4+fcyc:If fcyc=3 Then fact=1
  Case 3;turn left to right
   fa=fa0+13+fcyc:If fcyc=3 Then fact=0
 End Select
EndIf
If fscrx>=16 AND fscrx<=288 AND fscry>=32 AND fscry<=240;on screen
 nbob+1:xbob(nbob)=fscrx:ybob(nbob)=fscry:abob(nbob)=fa
 If friend>0
  nbob+1:xbob(nbob)=fscrx:ybob(nbob)=fscry-16:abob(nbob)=bu0+fcyc
 Else
  If body=2
   ResetList eb()
   USEPATH eb()
   While NextItem(eb())
    If ShapesHit(\a,\x,\y,fa,fmx,fmy) Then friend=1:noise{1,9,2}
   Wend
  EndIf
 EndIf
EndIf
Return

.generic
nbob=-1
ResetList alien()
USEPATH alien()
If monadr<>-1
 If AddItem(alien())
 \x=(monadr&fwm1) ASL 4:\y=(monadr ASR fsh)ASL 4
 \u=0:\v=0:\hm=monadr:Poke.b &n+1,Peek.b(&maparray(monadr))
 \id=n/16:\z=ghits(\id):maparray(monadr)=gtile(\id)
 EndIf
EndIf
While NextItem(alien())
xflag=-1
typ=\id ASR 1
If typ&1=1 AND typ<6 Then typ-1
 Select typ
 Case 0;static
  scrx=144+QWrap(\x-zmx,-xlim,xlim)
  scry=128+QWrap(\y-zmy,-ylim,ylim)
  Use BitMap 6:p=Point(6+scrx/16,6+scry/16)
  If p=1
   nbob+1:xbob(nbob)=scrx:ybob(nbob)=scry:abob(nbob)=((cyc+\z)ASR gspd(\id))&3+ga0(\id):\a=abob(nbob)
  EndIf
  If p<1 Then xflag=0:maparray(\hm)=4+\id ASL 4: KillItem alien()
 Case 2;patroller
  If \u=0 Then \u=gspd(\id):;If cyc&1 Then \u=-\u
  If \u<0
   \a=ga0(\id)+(cyc+\z)&3
   y0=(\y ASR 4):If y0=fhm1 Then y0=0
   x0=\x ASR 4:adr=x0-1+(y0 ASL fsh):If x0=0 Then adr+fw
   If typ(maparray(adr+fw)&15)<>1 Then \u=gspd(\id):\a+4
  Else
   \a=ga0(\id)+4+(cyc+\z)&3
   y0=(\y ASR 4):If y0=fwm1 Then y0=0
   x0=\x ASR 4:adr=x0+1+(y0 ASL fsh):If x0=fwm1 Then adr-fw
   If typ(maparray(adr+fw)&15)<>1 Then \u=-gspd(\id):\a-4
  EndIf
  \x=QWrap(\x+\u,0,fmw)
  scrx=144+QWrap(\x-zmx,-xlim,xlim)
  If scrx<0 Then \u=Abs(\u)
  If scrx>304 Then \u=-Abs(\u)
  scry=128+QWrap(\y-zmy,-ylim,ylim)
  Use BitMap 6:p=Point(6+scrx/16,6+scry/16)
  If p=1
   nbob+1:xbob(nbob)=scrx:ybob(nbob)=scry:abob(nbob)=\a
  EndIf
  If p<1 Then xflag=0:maparray(\hm)=4+\id ASL 4: KillItem alien()
 Case 4;homer/pulsar
  If (cyc+\z)&7=0
   dx=QWrap(\x-tgx,-xlim,xlim):dy=QWrap(\y-tgy+16,-ylim,ylim)
   spd0=-gspd(\id):dist=Abs(dx)+Abs(dy):\u=spd0*dx/dist:\v=spd0*dy/dist
  EndIf
  \x=QWrap(\x+\u,0,fmw):\y=QWrap(\y+\v,0,fmh)
  scrx=144+QWrap(\x-zmx,-xlim,xlim)
  scry=128+QWrap(\y-zmy,-ylim,ylim)
  Use BitMap 6:p=Point(6+scrx/16,6+scry/16)
  If p=1
   nbob+1:xbob(nbob)=scrx:ybob(nbob)=scry
   If \id<10 Then tmp=Sgn(\u)+Sgn(\v)*3:abob(nbob)=ga0(\id)+3+tmp:If tmp<0 Then abob(nbob)+1
   If \id>9 Then abob(nbob)=((\z+cyc)&7)ASR 1+ga0(\id)
   \a=abob(nbob)
  EndIf
  If p<1 Then xflag=0:maparray(\hm)=4+\id ASL 4: KillItem alien()
 Case 6;faller
  \v+2:\x=QWrap(\x+\u,0,fmw):\y=QWrap(\y+QLimit(\v,-8,8),0,fmh)
  If \v>=8
   x0=\x ASR 4:y0=\y ASR 4:adr=x0+y0 ASL fsh
   t0=typ(maparray(adr)&15)
   If t0=1 Then \v=-6:\u=gspd(\id)*Sgn(Rnd(2)-1)
   If t0=2 Then \v=-6:\u=-gspd(\id)
   If t0=3 Then \v=-6:\u=gspd(\id)
  EndIf
  scrx=144+QWrap(\x-zmx,-xlim,xlim)
  scry=128+QWrap(\y-zmy,-ylim,ylim)
  Use BitMap 6:p=Point(6+scrx/16,6+scry/16)
  If p=1
   \a=ga0(\id):If \v<0 Then \a+1
   nbob+1:xbob(nbob)=scrx:ybob(nbob)=scry:abob(nbob)=\a
  EndIf
  If p<1 Then xflag=0:maparray(\hm)=4+\id ASL 4: KillItem alien()
 Case 7;bouncer
  If \u=0 Then \v=8:\u=-gspd(\id):\a=ga0(\id)
  If \v=8 Then \v=-10:\u=-\u
  \v+2
  \x=QWrap(\x+\u,0,fmw):\y=QWrap(\y+\v,0,fmh)
  scrx=144+QWrap(\x-zmx,-xlim,xlim)
  scry=128+QWrap(\y-zmy,-ylim,ylim)
  Use BitMap 6:p=Point(6+scrx/16,6+scry/16)
  If p=1
   nbob+1:xbob(nbob)=scrx:ybob(nbob)=scry:abob(nbob)=\a
  EndIf
  If p<1 Then xflag=0:maparray(\hm)=4+\id ASL 4: KillItem alien()
 Case 8;bullet
  \v+\z
  \x=QWrap(\x+\u,0,fmw):\y=QWrap(\y+QLimit(\v,-8,8),0,fmh)
  adr=(\x ASR 4)+(\y ASR 4) ASL fsh
  scrx=144+QWrap(\x-zmx,-xlim,xlim)
  scry=128+QWrap(\y-zmy,-ylim,ylim)
  Use BitMap 6:p=Point(6+scrx/16,6+scry/16)
  If p=1
   nbob+1:xbob(nbob)=scrx:ybob(nbob)=scry:abob(nbob)=(cyc+\z)&3+ga0(16)
  EndIf
  If p<1 OR maparray(adr)&15>12 Then \id=19:\z=4:noise{2,8,Abs(scrx-144)+Abs(scry-128)}
 Case 9;explosion
  scrx=144+QWrap(\x-zmx,-xlim,xlim)
  scry=128+QWrap(\y-zmy,-ylim,ylim)
  Use BitMap 6:p=Point(6+scrx/16,6+scry/16)
  If p=1
   nbob+1:xbob(nbob)=scrx:ybob(nbob)=scry
   abob(nbob)=ga0(\id)-\z
  EndIf
  \z-1
  If \z<0 Then xflag=0:KillItem alien()
End Select
If xflag<>0
 If (gbul(\id)<>0 AND (\z+cyc)&31=0) OR (gbul(\id)=2 AND (\z+cyc)&15=0)
  x0=\x:y0=\y:bul0=gbul(\id)
  If AddItem(alien())
   noise{2,sbul,Abs(scrx-144)+Abs(scry-128)}
   \x=x0:\y=y0:\id=16
   Select bul0
   Case 1;homing
    dx=QWrap(\x-tgx,-xlim,xlim):dy=QWrap(\y-tgy+16,-ylim,ylim)
    spd0=-gspd(16):dist=Abs(dx)+Abs(dy):\u=spd0*dx/dist:\v=spd0*dy/dist:\z=0
   Case 2;spraying
    \v=-8:\z=2:\u=Rnd(9)-5
   Case 3;left
    \u=-gspd(16):\v=0:\z=0
   Case 4;right
    \u=gspd(16):\v=0:\z=0
   End Select
  EndIf
 EndIf
EndIf
Wend
Return

CNIF #devo=0
.gcoll
ResetList eb()
Use BitMap 7
While NextItem(eb())
 If eb()\id>=1
  x0=eb()\x:y0=eb()\y:a0=eb()\a
  ResetList alien()
  While NextItem(alien())
   If alien()\id<17
    USEPATH alien()
    x1=QWrap(\x-x0,-xlim,xlim):y1=QWrap(\y-y0,-ylim,ylim)
    If ShapesHit(a0,0,0,\a,x1,y1)
     If BlitColl(\a,104+x1,48+y1)
      oid=\id:\z-dam:If \z<=0 Then \id=18+(\id ASR 4):\z=4:noise{2,8,Abs(\x-zmx)+Abs(\y-zmy)}
      If body<>4 AND oid<>16 Then eb()\id=0:eb()\z=6:sfx(1)=sfx3:If lev=5 Then eb()\z=4
     EndIf
    EndIf
   EndIf
  Wend
 EndIf
Wend
If spell=0
x0=zmx+zx(db):y0=zmy+zy(db)
ResetList alien()
While NextItem(alien())
 If alien()\id<17
  USEPATH alien()
  x1=QWrap(\x-x0,-xlim,xlim):y1=QWrap(\y-y0,-ylim,ylim)
  If ShapesHit(za,0,0,\a,x1,y1)
   If BlitColl(\a,48+x1,64+y1) Then Gosub strike
  EndIf
 EndIf
Wend
EndIf
Return

.strike
If hit(dtype)>0 Then Return
hit(dtype)=16:pips(dtype)-1:If pips(dtype)<0 Then pips(dtype)=0
Use Slice 0:ShowSprite (pips(dtype)/p0(dtype)),264,24+(dtype ASL 5),dtype ASL 1
If pips(dtype)>0 Then Return
For i=0 To 15
USEPATH token(i)
\x=144:\y=128
\u=Rnd(9)-5:\v=-(Rnd(5)*2+12)
\id=i&3:If \id=3 Then \id=2
Next
ntoken=15:ttime=32:zu=0:zv=0
Sound 6,1,63
Return
CEND

.bodyload
QAMIGA
Select body
Case1:b$="elc":Case2:b$="atm":Case3:b$="ice":Case4:b$="ind"
End Select
LoadShapes 294,371,"Movement/"+b$+".shp"
For i=0 To 21
CopyShape 298+i,320+i
XFlip 320+i
x0=Peek.w(Addr Shape(320+i)+10):y0=Peek.w(Addr Shape(320+i)+12)
Handle 320+i,ShapeWidth(320+i)-x0,y0
Next
zdir=298:grav=2:acc=2:zbv=-32:zjv=-14:speed=4
egrav=0:eu=12:ev=12:ev0=0:etime=18:dam=2:p0(1)=2:sfxt=16
If body=1 Then acc=6:zjv=-20:speed=6:p0(0)=2:sfx1=3:sfx2=12:sfx3=7
If body=2 Then zjv=-16:egrav=2:eu=6:ev=4:ev0=-8:p0(0)=3:sfx1=1:sfx2=11:sfx3=11
If body=3
egrav=2:eu=6:ev=4:ev0=-8:p0(1)=3:p0(0)=4:sfxt=32:sfx1=1:sfx2=3:sfx3=7
For i=361 To 365
CopyShape i,i+5:XFlip i+5
Handle i+5,ShapeWidth(i)-Peek.w(Addr Shape(i)+10),Peek.w(Addr Shape(i)+12)
Next
EndIf
If body=4 Then speed=2:p0(1)=3:p0(0)=6:sfx1=2:sfx2=13:sfx3=7
VWait 250
BLITZ
If lev=1
pips(0)=p0(0)*8:pips(1)=p0(1)*8
EndIf
BlitMode CookieMode
Use BitMap 7:For i=352 To 355:Blit i,104,48:Next
bodyflag=1
Return

.levload
fx0=16+zx(db):fy0=16+zy(db):bu0=342
ClrInt 5
Use Slice 1:For i=8 To 11:RGB i,0,0,0:Next:RGB 0,0,0,0
QAMIGA
LoadBitMap 3,"Stillness/"+a$+".ttl"
If ReadFile(0,"Form/"+a$+".hdr")
 ReadMem 0,&hdr(0),62
 fw=hdr(0):fh=hdr(1):bw=hdr(2):bh=hdr(3)
 For i=0 To 26
 r(i)=Int(hdr(i+4)/256):hdr(i+4)-(r(i)*256)
 g(i)=Int(hdr(i+4)/16):hdr(i+4)-(g(i)*16)
 b(i)=Int(hdr(i+4))
 Next
EndIf
If ReadFile(0,"Form/"+a$+".geo")
 ReadMem 0,&maparray(0),fw*fh+bw*bh
 CloseFile 0
EndIf
For i=0 To 244
If Peek.w(Addr Shape(i))<>0 Then Free Shape i
Next
LoadShapes 0,239,"Movement/"+a$+".shp"
For i=372 To 450
If Peek.w(Addr Shape(i))<>0 Then Free Shape i
Next
LoadShapes 372,"Movement/"+a$+".bob"
If (lev>3 AND lev<8) OR (lev>11 AND lev<15) OR lev=16 Then For i=0 To 15:CopyShape gtile(i),i*16+4:Next
fwm1=fw-1:fhm1=fh-1:size=fh*fw:fmw=16*fw:fmh=16*fh
xlim=fw ASL 3:ylim=fh ASL 3
For i=4 To 9
If (fw BitTst i) Then fsh=i
Next
VWait 250
BLITZ
For i=0 To 15:gun(i)=0:Next
dimflag=0
Use Slice 0
Select lev
Case 1:Restore tzc
Case 4:Restore hel
Case 5:Restore rem
For i=4 To 52 Step 16:CopyShape 0,i:Next:For i=16 To 48 Step 16:CopyShape 0,i:Next
Case 6:Restore anx
Case 7:Restore cjk
End Select
Read nswitch
For i=0 To nswitch
 Read x1,y1,x2,y2,sww(i),swh(i):swpos(i)=x1+y1*fw:swadr(i)=x2+y2*fw
 For j=0 To sww(i)*swh(i)-1:Read swinf(i,j):Next
 If (swset(lev) BitTst i)
  src=0:For j0=0 To swh(i)-1:For i0=0 To sww(i)-1
   adr=swadr(i)+i0+j0*fw:Exchange maparray(adr),swinf(i,src):src+1
  Next:Next
 maparray(swpos(i)-fw)=0:maparray(swpos(i))=25
 EndIf
Next
Read ndoors
For i=0 To ndoors
Read drpx(i),drpy(i),drx(i),dry(i),drlev(i)
Next
Return

.veil_of_maya
gate=0
zadr=nzcx+nzcy ASL fsh
Use BitMap 4:Cls 0
Use Slice 1
For i=1 To 3
 RGB i+8,bmc(i) ASR 8,(bmc(i)ASR 4)&15,bmc(i)&15
Next
Use Slice 0
 ShowSprite (pips(0)/p0(0)),264,24,0
 ShowSprite (pips(1)/p0(1)),264,56,2
For i=0 To 3:FlushBuffer i:Next:ClearList alien():nbob=0
For i=1 To 11:Poke.w copa(i),0:Next
For i=9 To 15:RGB i,0,0,0:Next;FadeOut 0,1,9,15
Use BitMap db:ReMap 0,7:Use BitMap 1-db:Scroll 0,0,304,240,0,0,db
Use BitMap 2
For i=0 To 18
For j=0 To 14
adr=QWrap(nzcx-9+i,0,fw)+fw*QWrap(nzcy-8+j,0,fh)
Poke.b &n+1,Peek.b(&maparray(adr))
Block n,i*16,j*16
If n&15=4
 If AddItem(alien())
 USEPATH alien()
 \x=(adr&fwm1) ASL 4:\y=(adr ASR fsh)ASL 4
 \u=0:\v=0:\hm=adr
 \id=n/16:\z=ghits(\id):maparray(adr)=gtile(\id)
 EndIf
EndIf
Next
Next
For k=9 To 15:RGB k,r(k-8),g(k-8),b(k-8):Next;Fade in new fg pal to bg screen
ShowB 2,16,16,fx0
BlitMode EraseMode
Use BitMap 1-db
Scroll fx0,fy0,256,192,0,0
For i=0 To 15:Block 255,i*16,80:Block 255,i*16,96:Next
For j=0 To 11:Block 255,112,j*16:Block 255,128,j*16:Next
VWait:ShowF 1-db,0,0,16
Use BitMap db:Scroll 0,0,256,192,0,0,1-db
tx=64:ty=0
Restore veil_of_maya
For i=0 To 15:Read sine(i):Next
Data.w 0,50,91,118,128,118,91,50,0,-50,-91,-118,-128,-118,-91,-50
Use BitMap db
Scroll 4,3,124,93,0,0:Scroll 128,3,124,93,132,0
Scroll 4,96,124,93,0,99:Scroll 128,96,124,93,132,99
VWait:ShowF db,0,0,16:db=1-db
t01=2:t02=2:If body=0 Then t01=1:t02=0
For i=0 To 15 Step 4:token(i)\id=0:token(i+1)\id=t02:token(i+2)\id=1:token(i+3)\id=t01:Next
For i=2 To 32
Use BitMap db
sizex=128-(i-1)*4:sizey=96-(i-1)*3
Scroll 8,6,sizex,sizey,0,0
Scroll 120+i*4,6,sizex,sizey,128+i*4,0
Scroll 8,90+i*3,sizex,sizey,0,96+i*3
Scroll 120+i*4,90+i*3,sizex,sizey,128+i*4,96+i*3
Boxf 0,96-i*3,255,95+i*3,0
Boxf 128-i*4,0,127+i*4,191,0
tu=50*tx+87*ty:tv=-87*tx+50*ty
tspd=Abs(tu)+Abs(tv):tu=-8*tu/tspd:tv=-8*tv/tspd
tx+tu:ty+tv
For j=0 To 15
USEPATH token(j)
cs=sine((j+4)&15):sn=sine(j)
\x=128+((cs*tx+sn*ty)ASR 7)
\y=96+((cs*ty-sn*tx)ASR 7)
\a=286+\id*4+(i&3)
BBlit db,\a,\x,\y
Next
VWait:ShowF db,0,0,16:db=1-db:UnBuffer db
Next
For i=0 To 15:USEPATH token(i)
cs=sine((i+4)&15):sn=sine(i)
\x+16:\y+16
\u=(cs*tu+sn*tv)ASR 7
\v=((cs*tv-sn*tu)ASR 7)-8
Next
For i=1 To 7:RGB i,r(i),g(i),b(i):Next
Use BitMap db:UnBuffer db:Scroll 0,0,304,240,0,0,2
For i=0 To 15:USEPATH token(i):\x+\u:\y+\v:BBlit db,\a,\x,\y:Next
VWait:ShowF db,16,16,16
Use BitMap 1-db:UnBuffer 1-db:Scroll 0,0,304,240,0,0,2
For i=9 To 15:RGB i,0,0,0:Next
bx=(nzcx+bw)*8:by=(nzcy+bh)*8
While bx>bw*8:bx-bw*8:Wend
While by>bh*8:by-bh*8:Wend
j0=by ASR 4:ShowB 2,bx,by,16
y1=j0
Sound 6,1,63
For j=0 To 23
 Use BitMap 2
 x0=0:y1=QWrap(y1+1,0,bh):adr=size+y1*bw:y0=QWrap(j+j0,0,24)ASL 4
 For i=0 To 31
  Poke.b &n+1,Peek.b(&maparray(adr)):adr+1:If i=bw-1 Then adr-bw
  Block n,x0,y0:x0+16
 Next
 Use BitMap db
 UnBuffer db
 For k=0 To 15:USEPATH token(k)
  \v=\v+2:If \v>8 Then \v=8
  \x+\u:\y+\v:\a=286+\id*4+(k+j)&3:If \y<224 Then BBlit db,\a,\x,\y
 Next
 BBlit db,zdir,144,128
 VWait:ShowF db,16,16,bx:db=1-db
 If j>13
  fcol=j-13
  For k=9 To 15
   r0=r(k):If r0>fcol Then r0=fcol
   g0=g(k):If g0>fcol Then g0=fcol
   b0=b(k):If b0>fcol Then b0=fcol
   RGB k,r0,g0,b0
  Next
  fcol ASL 1
  For k=1 To 11
   r0=r(k+15):If r0>fcol Then r0=fcol
   g0=g(k+15):If g0>fcol Then g0=fcol
   b0=b(k+15):If b0>fcol Then b0=fcol
   Poke.w copa(k),(r0 ASL 8)+(g0 ASL 4)+b0
  Next
 EndIf
Next
For k=9 To 15:PalRGB 0,k,r(k),g(k),b(k):Next
For i=0 To 1:zcx(i)=nzcx:zcy(i)=nzcy:zx(i)=0:zy(i)=0:Next:zu=0:ozu=0:zv=0:zvv=0:ozv=0:air=0:gate=0
SetInt 5:clock+1:End SetInt
Return

.initplat
For i=0 To 5:typ(i)=0:Next
For i=6 To 10:typ(i)=1:Next
typ(11)=2:typ(12)=3
For i=13 To 15:typ(i)=1:Next
icadr=-1:nun=0:vsfx(0)=31:vsfx(1)=63:vsfx(2)=63:vsfx(3)=63
Return
