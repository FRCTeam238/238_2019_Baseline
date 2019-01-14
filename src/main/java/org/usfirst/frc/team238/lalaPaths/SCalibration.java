package org.usfirst.frc.team238.lalaPaths;

import java.util.ArrayList;

public class SCalibration{
	public static Object[][] objects = new Object[][]{
		{0.01 , 0.04332990011366653 , 0.036670099886333477 , 0.012516370220036963},
		{0.02 , 0.12998970034099958 , 0.11001029965900042 , 0.012516370220036963},
		{0.03 , 0.25997940068199915 , 0.22002059931800083 , 0.012516370220036963},
		{0.04 , 0.4332990011366653 , 0.36670099886333474 , 0.012516370220036963},
		{0.05 , 0.6499485017049981 , 0.5500514982950022 , 0.012516370220036963},
		{0.06 , 0.9099279023869973 , 0.770072097613003 , 0.012516370220036963},
		{0.07 , 1.213237203182663 , 1.0267627968173374 , 0.012516370220036963},
		{0.08 , 1.5598764040919952 , 1.320123595908005 , 0.012516370220036963},
		{0.09 , 1.949845505114994 , 1.6501544948850067 , 0.012516370220036963},
		{0.1 , 2.3831445062516594 , 2.0168554937483414 , 0.02504128823383201},
		{0.11 , 2.8600230521233065 , 2.419976947876693 , 0.02504128823383201},
		{0.12 , 3.3800272434184535 , 2.859972756581546 , 0.03757476060431979},
		{0.13 , 3.9437097493831144 , 3.3362902506168863 , 0.05011679389831521},
		{0.14 , 4.550832469655373 , 3.849167530344621 , 0.06266739468652618},
		{0.15 , 5.201406976855768 , 4.398593023144234 , 0.07522656954352819},
		{0.16 , 5.895444872023729 , 4.984555127976275 , 0.08779432504780242},
		{0.17 , 6.632957784670411 , 5.607042215329582 , 0.10037066778167218},
		{0.18 , 7.413957372838024 , 6.266042627161966 , 0.11295560433134104},
		{0.19 , 8.23881814288623 , 6.961181857113768 , 0.13815128524224088},
		{0.2 , 9.107668547419058 , 7.692331452580948 , 0.16338142054739263},
		{0.21 , 10.020206795814607 , 8.4597932041854 , 0.18864606307544057},
		{0.22 , 10.976457095039468 , 9.263542904960518 , 0.21394526571659184},
		{0.23 , 11.97644376958705 , 10.103556230412936 , 0.2392790814225401},
		{0.24 , 13.020191261934034 , 10.97980873806599 , 0.2646475632063763},
		{0.25 , 14.108354422162027 , 11.891645577837927 , 0.3027654008950234},
		{0.26 , 15.241115318548658 , 12.838884681451319 , 0.34096153607424773},
		{0.27 , 16.4179324886157 , 13.822067511384276 , 0.3792361484364831},
		{0.28 , 17.61712188740775 , 14.822878112592246 , 0.4175894179819013},
		{0.29 , 18.81782899046366 , 15.822171009536344 , 0.468849778013266},
		{0.3 , 20.019319895621628 , 16.820680104378376 , 0.5073872827086968},
		{0.31 , 21.221600293271173 , 17.818399706728837 , 0.5588939460251922},
		{0.32 , 22.426733539828252 , 18.813266460171757 , 0.6234760787567588},
		{0.33 , 23.632906615698055 , 19.807093384301954 , 0.6753013530876527},
		{0.34 , 24.840129060931915 , 20.79987093906818 , 0.7402831150405399},
		{0.35000000000000003 , 26.048408375043167 , 21.79159162495685 , 0.7924291877099133},
		{0.36 , 27.257754328382102 , 22.782245671617783 , 0.857813319767142},
		{0.37 , 28.470859287225828 , 23.769140712774234 , 0.936570994423008},
		{0.38 , 29.68530753737755 , 24.754692462622277 , 1.0024506538040128},
		{0.39 , 30.901113982811914 , 25.738886017188197 , 1.0818054408054483},
		{0.4 , 32.11983482598446 , 26.720165174015843 , 1.1614880227826923},
		{0.41000000000000003 , 33.34007813896067 , 27.699921861039275 , 1.241499886083563},
		{0.42 , 34.56354593084759 , 28.676454069152538 , 1.3352652242052996},
		{0.43 , 35.7904712740604 , 29.64952872593964 , 1.429483155492679},
		{0.44 , 37.0192640052402 , 30.620735994759933 , 1.5241560634333162},
		{0.45 , 38.24994722574701 , 31.590052774252946 , 1.6192863396663597},
		{0.46 , 39.48254426969993 , 32.55745573029993 , 1.7148763838784478},
		{0.47000000000000003 , 40.719145590008104 , 33.52085440999204 , 1.8246882187509827},
		{0.48 , 41.96001586337003 , 34.47998413663002 , 1.93510731821602},
		{0.49 , 43.203201066286766 , 35.436798933712836 , 2.0461373017998614},
		{0.5 , 44.45105238606844 , 36.38894761393145 , 2.171780754037375},
		{0.51 , 45.703863990143404 , 37.336136009856574 , 2.298207145178295},
		{0.52 , 46.95944723805291 , 38.28055276194715 , 2.425421686081776},
		{0.53 , 48.217844778439144 , 39.22215522156111 , 2.5534296069348206},
		{0.54 , 49.48178802132636 , 40.158211978673364 , 2.6965975223406526},
		{0.55 , 50.74882388673106 , 41.09117611326843 , 2.826297616195108},
		{0.56 , 52.021894638894196 , 42.018105361106045 , 2.9859201736452117},
		{0.5700000000000001 , 53.301352646494436 , 42.93864735350564 , 3.1320894431427773},
		{0.58 , 54.58448304870656 , 43.855516951293524 , 3.2940482781764016},
		{0.59 , 55.874555522567775 , 44.765444477432666 , 3.4572450531336703},
		{0.6 , 57.16865136260843 , 45.67134863739074 , 3.6216895577175436},
		{0.61 , 58.470287004909984 , 46.569712995089944 , 3.802518134587309},
		{0.62 , 59.77632732527503 , 47.46367267472527 , 3.9696032998376225},
		{0.63 , 61.0905517840885 , 48.349448215911096 , 4.168716261220705},
		{0.64 , 62.4134164093496 , 49.22658359064976 , 4.354111506526937},
		{0.65 , 63.741468166013625 , 50.09853183398755 , 4.556703606962514},
		{0.66 , 65.07889407037868 , 50.961105929620345 , 4.76112886485656},
		{0.67 , 66.4262272673597 , 51.813772732640565 , 4.9833481350218385},
		{0.68 , 67.78402001956475 , 52.655979980435184 , 5.207733145568084},
		{0.6900000000000001 , 69.14831809370247 , 53.49168190629855 , 5.434304534744535},
		{0.7000000000000001 , 70.51925820834037 , 54.320741791659735 , 5.663082961231119},
		{0.71 , 71.90184305300932 , 55.13815694699009 , 5.910675322661839},
		{0.72 , 73.29671058645859 , 55.943289413540434 , 6.160850423611717},
		{0.73 , 74.70454294240743 , 56.73545705759198 , 6.4305792458143145},
		{0.74 , 76.12604643266172 , 57.51395356733837 , 6.70330636049152},
		{0.75 , 77.55638483955734 , 58.283615160443844 , 6.979062505956181},
		{0.76 , 79.00153789633411 , 59.03846210366597 , 7.275406613963304},
		{0.77 , 80.46230185553942 , 59.777698144461 , 7.575241447984318},
		{0.78 , 81.93952485282347 , 60.50047514717728 , 7.8965589062719275},
		{0.79 , 83.43408396568567 , 61.205916034315806 , 8.221873723018474},
		{0.8 , 84.94025431760606 , 61.89974568239359 , 8.551228590801287},
		{0.81 , 86.46518458758237 , 62.574815412418715 , 8.903310661115736},
		{0.8200000000000001 , 88.0169922343551 , 63.22300776564421 , 9.278891450028055},
		{0.8300000000000001 , 89.58268349030598 , 63.8573165096955 , 9.640463644524468},
		{0.84 , 91.17023471976566 , 64.46976528023536 , 10.04556249891254},
		{0.85 , 92.78074502080754 , 65.05925497919095 , 10.436763448047078},
		{0.86 , 94.41538780180446 , 65.62461219819481 , 10.873226116055864},
		{0.87 , 96.07534861637716 , 66.16465138362206 , 11.295913786296989},
		{0.88 , 97.76187798388477 , 66.67812201611342 , 11.765724597595595},
		{0.89 , 99.48537426999022 , 67.15462573001115 , 12.242787542255584},
		{0.9 , 101.22920799514874 , 67.61079200485227 , 12.727174746709453},
		{0.91 , 103.0035305413617 , 68.03646945863963 , 13.240505601953485},
		{0.92 , 104.76129694099299 , 68.39870305900803 , 13.761960623883661},
		{0.93 , 106.50317630923438 , 68.6968236907648 , 14.313856872316489},
		{0.9400000000000001 , 108.24060168268407 , 68.91939831731867 , 14.897338491232839},
		{0.9500000000000001 , 109.96399855995737 , 69.07600144004218 , 15.490589580860545},
		{0.96 , 111.67381242140027 , 69.1661875786025 , 16.117069317630804},
		{0.97 , 113.3703978844417 , 69.18960211555822 , 16.75421994497968},
		{0.98 , 115.05394415761631 , 69.14605584238215 , 17.426296108203342},
		{0.99 , 116.73705271567646 , 69.02294728432275 , 18.13458074235534},
		{1.0 , 118.40770747513098 , 68.83229252487108 , 18.85531763445825},
		{1.01 , 120.05229684409284 , 68.58770315590782 , 19.58852091478329},
		{1.02 , 120.38552380638039 , 67.5493980745645 , 20.36011910892707},
		{1.03 , 120.40193856434527 , 66.2860661409529 , 21.17141279519983},
		{1.04 , 120.390867818015 , 65.00778206720295 , 21.97000133699243},
		{1.05 , 120.3923857507502 , 63.7600873114772 , 22.781748253941963},
		{1.06 , 120.40666021670967 , 62.503695321696036 , 23.634254408759237},
		{1.07 , 120.40648598759743 , 61.22734351836695 , 24.500525915008883},
		{1.08 , 120.40531211737833 , 59.962004877266935 , 25.3803641963489},
		{1.09 , 120.40308589271423 , 58.71075386819131 , 26.273532270254805},
		{1.1 , 120.41304219161282 , 57.46345093170808 , 27.209200135214722},
		{1.11 , 120.3951221987746 , 56.224373184976976 , 28.12856142534744},
		{1.12 , 120.40233984968448 , 55.02235148952122 , 29.090542674628345},
		{1.1300000000000001 , 120.38227194389717 , 53.836175403983304 , 30.034604234720153},
		{1.1400000000000001 , 120.38645096606712 , 52.69301588887482 , 31.021172212526377},
		{1.1500000000000001 , 120.38835621838584 , 51.549352453600726 , 32.05082529115667},
		{1.16 , 120.36443420677956 , 50.435633364992434 , 33.06055175503595},
		{1.17 , 120.3514818572907 , 49.3875330813782 , 34.08073071918177},
		{1.18 , 120.34786354096282 , 48.37494897380831 , 35.14300641253703},
		{1.19 , 120.3209483762314 , 47.40430880846702 , 36.18224095622592},
		{1.2 , 120.31351238721174 , 46.4964858909915 , 37.26254857846395},
		{1.21 , 120.29397855642334 , 45.629015147586784 , 38.350687432268465},
		{1.22 , 120.27312146766296 , 44.82361888184673 , 39.44570579790792},
		{1.23 , 120.25102354939386 , 44.08305262232189 , 40.54661079115005},
		{1.24 , 120.23464834730208 , 43.40295630467486 , 41.685945185763615},
		{1.25 , 120.20274672313046 , 42.78899505224538 , 42.79559977823129},
		{1.26 , 120.18279525143205 , 42.25381675175105 , 43.94166327485768},
		{1.27 , 120.15069826170142 , 41.78978801798306 , 45.05558003827567},
		{1.28 , 120.12759817164913 , 41.406487892415434 , 46.20365670915693},
		{1.29 , 120.09868547711412 , 41.09537215008541 , 47.35091212544058},
		{1.3 , 120.07118525541912 , 40.86366481787465 , 48.52973589232434},
		{1.31 , 120.03898344969254 , 40.71411566407588 , 49.6714909700808},
		{1.32 , 120.00940045464137 , 40.65007543233946 , 50.80866040363055},
		{1.33 , 119.97902926139507 , 40.66849184563035 , 51.97320222462383},
		{1.34 , 119.92376834687607 , 40.763752760137855 , 53.13029342245418},
		{1.35 , 119.8650791232679 , 40.94244198374601 , 54.278666583193285},
		{1.36 , 119.80403702238993 , 41.203484084631924 , 55.417095023725246},
		{1.37 , 119.74195617423065 , 41.54556493279268 , 56.54439892002695},
		{1.3800000000000001 , 119.67513069356788 , 41.97239041344469 , 57.69111858339272},
		{1.3900000000000001 , 119.49111976789032 , 42.43640133912424 , 58.79245217074595},
		{1.4000000000000001 , 119.19272281792479 , 42.93479828909274 , 59.910261830888714},
		{1.41 , 118.7767067663865 , 43.470814340628884 , 61.01185434975516},
		{1.42 , 118.25311653085573 , 44.03440457616467 , 62.09627909822013},
		{1.43 , 117.74253768405572 , 44.664983422953156 , 63.16266207922526},
		{1.44 , 117.24791219696289 , 45.35960891005678 , 64.21020692042643},
		{1.45 , 116.77213380473249 , 46.11538730228142 , 65.23819504638185},
		{1.46 , 116.31801827491044 , 46.92950283210635 , 66.24598509083816},
		{1.47 , 115.88827748097656 , 47.79924362605039 , 67.23301161888183},
		{1.48 , 115.48549742935693 , 48.7220236776606 , 68.19878323516099},
		{1.49 , 115.11212025975072 , 49.695400847266065 , 69.1428801581765},
		{1.5 , 114.75893734158346 , 50.72858376543977 , 70.09024711590031},
		{1.51 , 114.4391737460589 , 51.808347360973244 , 70.98938377170379},
		{1.52 , 114.15473601859182 , 52.93278508842931 , 71.89000657685348},
		{1.53 , 113.89555181494477 , 54.111969292071336 , 72.76664265211807},
		{1.54 , 113.67505247114703 , 55.33246863587266 , 73.61917519161524},
		{1.55 , 113.49481670675304 , 56.59270440026737 , 74.44754309324125},
		{1.56 , 113.35622600925421 , 57.89129509775461 , 75.25173632982778},
		{1.57 , 113.26047026587321 , 59.22705084114068 , 76.03179134611679},
		{1.58 , 113.19618668314723 , 60.61133442386818 , 76.8078859215784},
		{1.59 , 113.16422724302883 , 62.04329386398872 , 77.55872880989567},
		{1.6 , 113.07523916807583 , 63.45228193894172 , 78.28448298441602},
		{1.61 , 112.93235513897604 , 64.83516596803571 , 78.98534367554186},
		{1.62 , 112.73847607169022 , 66.18904503532876 , 79.66153395911196},
		{1.6300000000000001 , 112.4962724597591 , 67.5112486472599 , 80.31330059769209},
		{1.6400000000000001 , 112.20818851275521 , 68.79933259425651 , 80.94091014522206},
		{1.6500000000000001 , 111.86470870158011 , 70.06281240544311 , 81.5602133558183},
		{1.6600000000000001 , 111.47986982929372 , 71.28765127771943 , 82.13975327140622},
		{1.67 , 111.06700295390233 , 72.46051815311809 , 82.69602771090769},
		{1.68 , 110.60473156672982 , 73.60278954028345 , 83.24307661657534},
		{1.69 , 110.10603463510199 , 74.70148647191989 , 83.75316968992884},
		{1.7 , 109.5722979279855 , 75.75522317903206 , 84.25349079328441},
		{1.71 , 109.00453792358914 , 76.7629831834444 , 84.7187075149332},
		{1.72 , 108.41423754157032 , 77.71328356543702 , 85.16225649982546},
		{1.73 , 107.79136074735675 , 78.6161603596637 , 85.58444962641369},
		{1.74 , 107.13661622030435 , 79.47090488671752 , 85.98559331793199},
		{1.75 , 106.44079928271611 , 80.28672182430711 , 86.37572003042257},
		{1.76 , 105.72416837927042 , 81.04335272775745 , 86.72592288189676},
		{1.77 , 105.0766443630348 , 81.8108767439856 , 87.06568295830193},
		{1.78 , 104.4775765231248 , 82.60994458388842 , 87.38554003529023},
		{1.79 , 103.93512622793102 , 83.43239487908795 , 87.68575595195334},
		{1.8 , 103.44780420916192 , 84.27971689786854 , 87.9665810761567},
		{1.81 , 103.02292614512073 , 85.14459496190855 , 88.22161102340638},
		{1.82 , 102.65000180345689 , 86.0375193035578 , 88.46485213713913},
		{1.83 , 102.31895979702914 , 86.96856130998985 , 88.68937368361127},
		{1.84 , 102.0371771079032 , 87.93034399911146 , 88.89537526708081},
		{1.85 , 101.80319824582689 , 88.92432286118343 , 89.08304276979968},
		{1.86 , 101.61557386074757 , 89.95194724627864 , 89.2525480873719},
		{1.87 , 101.47285952862295 , 91.01466157838878 , 89.40404891076645},
		{1.8800000000000001 , 101.37361421146397 , 92.11390689555938 , 89.5376885493533},
		{1.8900000000000001 , 101.3082524978095 , 93.25926860921518 , 89.65640759329769},
		{1.9000000000000001 , 101.2753951942808 , 94.45212591273675 , 89.7565718037294},
		{1.9100000000000001 , 101.28172899596774 , 95.685792111057 , 89.83828805170131},
		{1.92 , 101.32572699042774 , 96.96179411658835 , 89.90164681837501},
		{1.93 , 101.4058535899231 , 98.28166751710025 , 89.946722156542},
		{1.94 , 101.5124165727549 , 99.65510453425408 , 89.97402086933693},
		{1.95 , 101.6437277285548 , 101.08379337847427 , 89.9821912382409},
		{1.96 , 101.80625036378251 , 102.56127074322777 , 89.97125929071926},
		{1.97 , 101.98998619327293 , 104.0975349137475 , 89.9402375839206},
		{1.98 , 102.19297145662644 , 105.69454965040264 , 89.8891435858661},
		{1.99 , 102.42161442545395 , 107.34590668156649 , 89.8179463277996},
		{2.0 , 102.66542941628323 , 109.06209169069852 , 89.72411475066934},
		{2.0100000000000002 , 102.8450863977111 , 110.7624347092862 , 89.60905836351509},
		{2.02 , 102.95979584586759 , 112.44772526115709 , 89.46917226278363},
		{2.0300000000000002 , 103.00871448792712 , 114.11880661908039 , 89.30680990316799},
		{2.04 , 103.00008651032395 , 115.76743459669359 , 89.1218215404912},
		{2.05 , 102.92403021959564 , 117.40349088741618 , 88.90893000332854},
		{2.06 , 102.77951668436496 , 119.02800442263674 , 88.67195928029636},
		{2.07 , 102.39297060202507 , 120.41806788669177 , 88.41067766989055},
		{2.08 , 100.80749576274577 , 120.42164127115112 , 88.124830698809},
		{2.09 , 99.21911241401668 , 120.42568101093892 , 87.81414193644183},
		{2.1 , 97.63642427730312 , 120.42009358954449 , 87.48622786435793},
		{2.11 , 96.06438152642183 , 120.43490167021021 , 87.12552551039887},
		{2.12 , 94.46974138414534 , 120.42981406149792 , 86.74811111675281},
		{2.13 , 92.89408928387789 , 120.4350726592646 , 86.34574164970125},
		{2.14 , 91.32111925818286 , 120.43001848494607 , 85.92830952613535},
		{2.15 , 89.75585246432088 , 120.44612639996541 , 85.47558820700809},
		{2.16 , 88.16651911544581 , 120.44112053082546 , 85.00828650178995},
		{2.17 , 86.59547081397568 , 120.44674109755182 , 84.51581972305644},
		{2.18 , 85.01649096989782 , 120.45237150548954 , 83.99783346025366},
		{2.19 , 83.44124759306081 , 120.44671124855113 , 83.46722290254705},
		{2.2 , 81.87471527248213 , 120.46326424777507 , 82.89775402081189},
		{2.21 , 80.2871141258801 , 120.45702033995576 , 82.31620748030674},
		{2.22 , 78.72160158599966 , 120.4617479025763 , 81.70900615935768},
		{2.23 , 77.15286541676323 , 120.46612602953886 , 81.07583441191069},
		{2.24 , 75.5942916281333 , 120.45826237794357 , 80.4327880137082},
		{2.25 , 74.05058548081324 , 120.47340119408094 , 79.74743353505033},
		{2.2600000000000002 , 72.49457353153015 , 120.46425015962714 , 79.05295023208863},
		{2.27 , 70.96932957412852 , 120.4662314231591 , 78.33271480910003},
		{2.2800000000000002 , 69.45090612495491 , 120.46743757658436 , 77.58653008818686},
		{2.29 , 67.95401248548089 , 120.45575034144738 , 76.83385759628464},
		{2.3000000000000003 , 66.48277026833986 , 120.46714515934418 , 76.03597229505239},
		{2.31 , 65.01344676038026 , 120.4534962961526 , 75.23268521597791},
		{2.32 , 63.58735187118362 , 120.45076618753659 , 74.40433100127619},
		{2.33 , 62.19434592152842 , 120.43509438936817 , 73.57311348951364},
		{2.34 , 60.83778834361167 , 120.44176835652908 , 72.69534345658224},
		{2.35 , 59.49719069530125 , 120.42378718591873 , 71.81614508700997},
		{2.36 , 58.21076918076206 , 120.41619524392081 , 70.91348790497491},
		{2.37 , 56.95879790220471 , 120.4071744321355 , 69.98759658284058},
		{2.38 , 55.74510213547403 , 120.39665913303496 , 69.03876004238184},
		{2.39 , 54.58389335220685 , 120.37428970793505 , 68.0931850897563},
		{2.4 , 53.4772206973475 , 120.37130765843726 , 67.1001780509654},
		{2.41 , 52.41014113004989 , 120.34655115616938 , 66.11247607336429},
		{2.42 , 51.42237751184049 , 120.32151778639653 , 65.13225673456482},
		{2.43 , 50.49608002686023 , 120.31354558493976 , 64.10565435581904},
		{2.44 , 49.61899188366479 , 120.28652608716541 , 63.08879859800841},
		{2.45 , 48.809171737768615 , 120.27461907465377 , 62.02592997731758},
		{2.46 , 48.06314730585918 , 120.23902292774447 , 61.00467322205993},
		{2.47 , 47.40004552413906 , 120.2244507034903 , 59.93898191299151},
		{2.48 , 46.79171444627721 , 120.20120853210621 , 58.858698530464636},
		{2.49 , 46.25848791174411 , 120.17197813389228 , 57.79533413369531},
		{2.5 , 45.80437662952436 , 120.1479558143638 , 56.71999977664171},
		{2.5100000000000002 , 45.421055158228796 , 120.12305403690115 , 55.63367209150744},
		{2.52 , 45.11035323925643 , 120.09738110261283 , 54.53737466916649},
		{2.5300000000000002 , 44.87385086955108 , 120.07105626573355 , 53.43217393607774},
		{2.54 , 44.713910956525325 , 120.04315807045437 , 52.35107238498954},
		{2.5500000000000003 , 44.62975391264875 , 120.01775780414985 , 51.23158602951643},
		{2.56 , 44.61982016794366 , 119.99082966654295 , 50.138779086328356},
		{2.57 , 44.68429444721538 , 119.96484084619448 , 49.041840382713715},
		{2.58 , 44.8200933674662 , 119.94099765773316 , 47.97425527863761},
		{2.59 , 45.010008536743676 , 119.87108248845293 , 46.87240301911507},
		{2.6 , 45.22877350515208 , 119.69231752005317 , 45.802139828892464},
		{2.61 , 45.515386138737696 , 119.52570488648207 , 44.732093353202984},
		{2.62 , 45.87161607261716 , 119.36947495259093 , 43.663268210745166},
		{2.63 , 46.29648491778819 , 119.22460610740838 , 42.596656168313295},
		{2.64 , 46.78887658042785 , 119.09221444478018 , 41.53323141589935},
		{2.65 , 47.34054654891901 , 118.98054447628039 , 40.50597578965302},
		{2.66 , 47.956177824669815 , 118.88491320054099 , 39.451588763228884},
		{2.67 , 48.63394894405159 , 118.80714208117685 , 38.43481140758626},
		{2.68 , 49.31721614021521 , 118.6438748850073 , 37.42440109051997},
		{2.69 , 50.01033973470769 , 118.39075129048031 , 36.42111227588999},
		{2.7 , 50.700555409322135 , 118.06053561587728 , 35.45664653682631},
		{2.71 , 51.38354555328669 , 117.65754547191858 , 34.50016025810151},
		{2.72 , 52.06502802329175 , 117.17606300190482 , 33.55224001134103},
		{2.73 , 52.741701725961036 , 116.6193892992556 , 32.61343696119428},
		{2.74 , 53.39944782930832 , 116.00164319588525 , 31.714083893281543},
		{2.75 , 54.08551150773222 , 115.4355795174759 , 30.824185390379196},
		{2.7600000000000002 , 54.80904008608553 , 114.91205093911393 , 29.9441425139934},
		{2.77 , 55.568882143275566 , 114.4322088819353 , 29.074323594747586},
		{2.7800000000000002 , 56.352582324271786 , 114.00850870095681 , 28.243532918357285},
		{2.79 , 57.1590000516762 , 113.64209097351167 , 27.42288443779593},
		{2.8000000000000003 , 57.94437727921406 , 113.2167137459797 , 26.612624969029955},
		{2.81 , 58.69462699712849 , 112.74646402810622 , 25.840369487403013},
		{2.82 , 59.41891741548431 , 112.22217360971524 , 25.051144827986754},
		{2.83 , 60.1033044792659 , 111.65778654593048 , 24.32620174649802},
		{2.84 , 60.745979866155984 , 111.05511115905206 , 23.584571066019222},
		{2.85 , 61.35631280825056 , 110.40477821693719 , 22.87932710339337},
		{2.86 , 61.92132004314577 , 109.7197709820537 , 22.183884084699898},
		{2.87 , 62.45065808265582 , 108.99043294254653 , 21.49830516774732},
		{2.88 , 62.93175235202027 , 108.22933867316453 , 20.847485087982292},
		{2.89 , 63.363596476980725 , 107.43749454822738 , 20.20588455228191},
		{2.9 , 63.75591382535574 , 106.60517719985229 , 19.57351986293816},
		{2.91 , 64.09701276771854 , 105.74407825747797 , 18.974191012620487},
		{2.92 , 64.38633460583283 , 104.85475641934649 , 18.383399678234046},
		{2.93 , 64.62340811724735 , 103.93768290795498 , 17.824258455929794},
		{2.94 , 64.8079275744125 , 102.99316345080149 , 17.272951394979458},
		{2.95 , 64.94928974903247 , 102.01180127618133 , 16.729451256516995},
		{2.96 , 65.03731706349069 , 101.00377396170283 , 16.215891676314314},
		{2.97 , 65.07197746831415 , 99.96911355688242 , 15.709436421440923},
		{2.98 , 65.06226420555753 , 98.89882681964481 , 15.210046039974381},
		{2.99 , 64.99886503236337 , 97.80222599282726 , 14.738938685242019},
		{3.0 , 64.88194391281309 , 96.67914711239499 , 14.274213239228684},
		{3.0100000000000002 , 64.7117068808462 , 95.52938414434432 , 13.83652366546913},
		{3.02 , 64.48841064967002 , 94.35268037552942 , 13.404566231327259},
		{3.0300000000000002 , 64.22019213257924 , 93.14089889260599 , 12.978296927470035},
		{3.04 , 63.89908556929899 , 91.90200545591216 , 12.577572269736761},
		{3.0500000000000003 , 63.525444712936824 , 90.63564631226839 , 12.181923608550097},
		{3.06 , 63.09964611315256 , 89.34144491205575 , 11.810719905940578},
		{3.0700000000000003 , 62.622080452255815 , 88.01901057294651 , 11.444020551259939},
		{3.08 , 62.09991780963132 , 86.66117321557958 , 11.081786553220986},
		{3.09 , 61.52639481532043 , 85.27469620987601 , 10.742700345664447},
		{3.1 , 60.9019409863138 , 83.85915003888566 , 10.407551351980905},
		{3.11 , 60.22699683237518 , 82.41409419283609 , 10.094605153789932},
		{3.12 , 59.50199843418196 , 80.93909259101167 , 9.785109214379487},
		{3.13 , 58.76714484700429 , 79.47394617818955 , 9.479032945576144},
		{3.14 , 58.01706970963284 , 78.02402131556659 , 9.194057295171149},
		{3.15 , 57.25251342389408 , 76.58857760129966 , 8.912057339651941},
		{3.16 , 56.4741921150554 , 75.16689891015001 , 8.650361525546266},
		{3.17 , 55.687818801627024 , 73.75327222356441 , 8.374051935273174},
		{3.18 , 54.88868951483836 , 72.35240151037019 , 8.134659109416836},
		{3.19 , 54.07743173219496 , 70.96365929300187 , 7.880608425244644},
		{3.2 , 53.259224839680535 , 69.58186618551895 , 7.645754161559239},
		{3.21 , 52.42538041088938 , 68.21571061430994 , 7.429612564377293},
		{3.22 , 51.58543081962974 , 66.85566020557543 , 7.198913966246915},
		{3.23 , 50.73949596955989 , 65.50159505564834 , 6.986599767477167},
		{3.24 , 49.87970300623754 , 64.16138801895868 , 6.792233452248679},
		{3.25 , 49.01467281505315 , 62.826418210146464 , 6.583406918203228},
		{3.2600000000000002 , 48.14450495418751 , 61.496586071009006 , 6.392234984751765},
		{3.27 , 47.265678104079804 , 60.175412921114 , 6.202578579184388},
		{3.2800000000000002 , 46.378650161976886 , 58.86244086322854 , 6.030048372490388},
		{3.29 , 45.48385173012064 , 57.55723929508469 , 5.858773056019058},
		{3.3000000000000003 , 44.584960916383814 , 56.25613010881278 , 5.688743751218906},
		{3.31 , 43.68204508133662 , 54.95904594387137 , 5.519951585863233},
		{3.3200000000000003 , 42.77212808284006 , 53.66896294236831 , 5.367570254872624},
		{3.33 , 41.85558780719601 , 52.38550321799759 , 5.21619737602747},
		{3.34 , 40.93562967257064 , 51.10546135263456 , 5.065826306308356},
		{3.35 , 40.009565702637715 , 49.83152532256465 , 4.931343407912338},
		{3.36 , 39.07773437086346 , 48.563356654338904 , 4.7976617741872065},
		{3.37 , 38.143017803380815 , 47.29807322182443 , 4.664776585376656},
		{3.38 , 37.205457010590465 , 46.03563401460908 , 4.532683030445411},
		{3.39 , 36.26271910246757 , 44.77837192274398 , 4.41592723675682},
		{3.4 , 35.31510360344746 , 43.525987421757854 , 4.2997897717908975},
		{3.41 , 34.3651052926601 , 42.27598573254512 , 4.184267276409409},
		{3.42 , 33.41275499187401 , 41.02833603332554 , 4.06935639840381},
		{3.43 , 32.45604314063586 , 39.78504788457571 , 3.9693084677641455},
		{3.44 , 31.49523654221791 , 38.5458544829755 , 3.869724009464571},
		{3.45 , 30.532479861692412 , 37.3086111635013 , 3.7706007919783957},
		{3.46 , 29.56598549648628 , 36.075105528719284 , 3.686003456096436},
		{3.47 , 28.59773363650376 , 34.843357388701236 , 3.5877309246060105},
		{3.48 , 27.62774532291524 , 33.613345702284086 , 3.50385946672709},
		{3.49 , 26.654444995811776 , 32.38664602938478 , 3.4203208214007232},
		{3.5 , 25.67806241913288 , 31.163028606076086 , 3.350958512075657},
		{3.5100000000000002 , 24.70027349688902 , 29.940817528313296 , 3.2680264133947},
		{3.52 , 23.721092782500804 , 28.719998242707607 , 3.1991676176422117},
		{3.5300000000000002 , 22.739210337673452 , 27.501880687531774 , 3.1305364106742544},
		{3.54 , 21.756092498484104 , 26.284998526720987 , 3.062131993913381},
		{3.5500000000000003 , 20.770557336940517 , 25.070533688265098 , 3.0075712137676294},
		{3.56 , 19.78280446585868 , 23.858286559337326 , 2.953154661794689},
		{3.5700000000000003 , 18.79409732345842 , 22.64699370174686 , 2.898881931224263},
		{3.58 , 17.804442487858317 , 21.436648537343977 , 2.8447526159242487},
		{3.59 , 16.81384650113522 , 20.22724452406707 , 2.7907663104078426},
		{3.6 , 15.821430021580001 , 19.019661003625618 , 2.7503701878830435},
		{3.61 , 14.827377279221189 , 17.8137137459811 , 2.7100541100817397},
		{3.62 , 13.832637852544268 , 16.608453172655118 , 2.6698179066672507},
		{3.63 , 12.837215330251766 , 15.403875694955893 , 2.629661407514291},
		{3.64 , 11.87683474404399 , 14.244256281162018 , 2.6029346043082286},
		{3.65 , 10.951694407178453 , 13.129396618016976 , 2.5762430994060326},
		{3.66 , 10.063082820220457 , 12.058008204983928 , 2.536271916464779},
		{3.67 , 9.209872756240852 , 11.031218268961434 , 2.5229657835605757},
		{3.68 , 8.392233595370305 , 10.048857429826745 , 2.496379872248094},
		{3.69 , 7.611151012314327 , 9.109940012890332 , 2.469829058501479},
		{3.7 , 6.865772556015036 , 8.215318469191526 , 2.456566797564692},
		{3.71 , 6.156240713656758 , 7.364850311549362 , 2.4433132922439915},
		{3.72 , 5.482982251693794 , 6.558108773505082 , 2.4300685362866354},
		{3.73 , 4.846009292285956 , 5.795081732913316 , 2.4168325234426185},
		{3.74 , 4.245333929291825 , 5.075757095913099 , 2.4036052474645544},
		{3.75 , 3.6809682282998124 , 4.400122796900186 , 2.3903867021078695},
		{3.7600000000000002 , 3.152924226714175 , 3.768166798486153 , 2.3771768811308527},
		{3.77 , 2.6612139337664327 , 3.179877091437503 , 2.3771768811308527},
		{3.7800000000000002 , 2.205611731663398 , 2.635479293540255 , 2.363975778294286},
		{3.79 , 1.7866501514001245 , 2.134440873802161 , 2.363975778294286},
		{3.8000000000000003 , 1.403903074749773 , 1.6771879504525125 , 2.363975778294286},
		{3.81 , 1.0576081006375502 , 1.2634829245647354 , 2.363975778294286},
		{3.8200000000000003 , 0.7477652290634561 , 0.8933257961388293 , 2.3507833873620174},
		{3.83 , 0.4744254961619928 , 0.5666655290402924 , 2.3507833873620174},
		{3.84 , 0.23746133833890343 , 0.28362968686338186 , 2.3507833873620174},
		{3.85 , 0.0 , 0.0 , 2.3507833873620174}
	};
}