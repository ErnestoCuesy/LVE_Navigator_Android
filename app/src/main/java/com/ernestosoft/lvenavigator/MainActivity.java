package com.ernestosoft.lvenavigator;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String[] placesNamesArr = new String[]{"South Gate on The Straight/Rockery/Forest","West Gate on Sunset/White Hills","Main clubhouse/Restaurant/Estate Management Office/Tennis Court","Wellness Centre/Gym/Hairdresser","Gazebo (Functions Venue)","Michael's Workshop","1 (Saint Sulpice)","2 (Saint Sulpice)","3 (Saint Sulpice)","4 (Saint Sulpice)","5 (Saint Sulpice)","6 (Saint Sulpice)","7 (Saint Sulpice)","8 (Saint Sulpice)","9 (Saint Sulpice)","10 (Saint Sulpice)","11 (Saint Sulpice)","12 (Saint Sulpice)","13 (Saint Sulpice)","14 (Saint Sulpice)","15 (Saint Sulpice)","16 (Rue St Merri)","17 (Rue St Merri)","18 (Rue St Merri)","19 (Rue St Merri)","20 (Rue St Merri)","21 (Rue St Merri)","22 (Rue St Merri)","23 (Rue St Merri)","24 (Rue St Merri)","25 (Rue St Merri)","26 (Rue St Merri)","27 (Rue St Merri)","28 (Rue St Merri)","29 (Rue St Merri)","30 (Rue St Merri)","31 (Rue St Merri)","32 (Rue St Merri)","33 (Rue St Merri)","34 (Rue St Merri)","35 (Rue St Merri)","36 (Saint Sulpice)","37 (Saint Sulpice)","38 (Saint Sulpice)","39 (Saint Sulpice)","40 (Rue St Merri)","41 (Rue St Merri)","42 (Rue St Merri)","43 (Rue St Merri)","44 (Rue St Merri)","45 (Rue St Merri)","46 (Rue St Merri)","47 (Rue St Merri)","48 (Blvd Voltaire)","49 (Blvd Voltaire)","50 (Blvd Voltaire)","51 (Ave De L'Opera)","52 (Ave De L'Opera)","53 (Ave De L'Opera)","54 (Ave De L'Opera)","55 (Ave De L'Opera)","56 (Ave De L'Opera)","57 (Ave De L'Opera)","58 (Ave De L'Opera)","59 (Ave De L'Opera)","60 (Blvd Voltaire)","61 (Blvd Voltaire)","62 (Blvd Voltaire)","63 (Blvd Voltaire)","64 (Rue De Turenne)","65 (Rue De Turenne)","66 (Rue De Turenne)","67 (Rue De Turenne)","68 (Rue De Turenne)","69 (Rue De Turenne)","70 (Rue De Turenne)","71 (Rue Sorbonne)","72 (Rue Sorbonne)","73 (Rue Sorbonne)","74 (Rue Sorbonne)","75 (Rue Sorbonne)","76 (Rue Sorbonne)","77 (Rue Pavee)","78 (Rue Pavee)","79 (Rue Pavee)","80 (Rue Pavee)","81 (Rue Sorbonne)","82 (Rue Sorbonne)","83 (Rue Sorbonne)","84 (Rue Sorbonne)","85 (Rue De Rivoli)","86 (Rue De Rivoli)","87 (Rue De Rivoli)","88 (Blvd St Michel)","89 (Blvd St Michel)","90 (Blvd St Michel)","91 (Blvd St Michel)","92 (Blvd St Michel)","93 (Blvd St Michel)","94 (Blvd St Michel)","95 (Blvd St Michel)","96 (Blvd St Michel)","97 (Blvd St Michel)","98 (Blvd St Michel)","99 (Blvd St Michel)","100 (Blvd St Michel)","101 (Blvd St Michel)","102 (Blvd Voltaire)","103 (Blvd Voltaire)","104 (Blvd Voltaire)","105 (Blvd Voltaire)","106 (Blvd Voltaire)","107 (Rue de Nicolas)","108 (Rue de Nicolas)","109 (Rue de Nicolas)","110 (Rue de Nicolas)","111 (Rue de Nicolas)","112 (Rue de Nicolas)","113 (Rue de Nicolas)","114 (Blvd Voltaire)","115 (Blvd Voltaire)","116 (Rue de Nicolas)","117 (Rue de Nicolas)","118 (Rue de Nicolas)","119 (Rue de Nicolas)","120 (Rue de Nicolas)","121 (Rue de Nicolas)","122 (Rue de Nicolas)","123 (Blvd Voltaire)","124 (Blvd Voltaire)","125 (Blvd Voltaire)","126 (Blvd Voltaire)","127 (Blvd Voltaire)","128 (Blvd Voltaire)","129 (Rue Beaborg)","130 (Rue Beaborg)","131 (Port Royal)","132 (Port Royal)","133 (Port Royal)","134 (Port Royal)","135 (Port Royal)","136 (Blvd Voltaire)","137 (Blvd Voltaire)","138 (Blvd Voltaire)","139 (Blvd Voltaire)","140 (Rue des Peres)","141 (Rue des Peres)","142 (Rue des Peres)","143 (Rue des Peres)","144 (Rue des Peres)","145 (Rue des Peres)","146 (Rue des Peres)","147 (Rue des Peres)","148 (Rue des Peres)","149 (Rue D'Arcole)","150 (Rue D'Arcole)","151 (Rue D'Arcole)","152 (Port Royal)","153 (Rue Beaborg)","154 (Rue Beaborg)","155 (Port Royal)","156 (Port Royal)","157 (Rue Beaborg)","158 (Rue Beaborg)","159 (Rue Beaborg)","160 (Rue Beaborg)","161 (Rue Beaborg)","162 (Rue Beaborg)","163 (Rue Beaborg)","164 (Rue Beaborg)","165 (Rue Beaborg)","166 (Rue Beaborg)","167 (Rue Beaborg)","168 (Rue De Lyon)","169 (Rue De Lyon)","170 (Rue De Lyon)","171 (Rue De Lyon)","172 (Rue De Lyon)","173 (Rue De Lyon)","174 (Rue des Peres)","175 (Rue des Peres)","176 (Rue De Lyon)","177 (Rue De Lyon)","178 (Rue De Lyon)","179 (Rue De Lyon)","180 (Rue De Lyon)","181 (Rue De Lyon)","182 (Rue des Peres)","183 (Rue des Peres)","184 (Rue des Peres)","185 (Rue des Peres)","186 (Rue des Peres)","187 (Rue des Peres)","188 (Rue De Lyon)","189 (Rue De Lyon)","190 (Rue De Lyon)","191 (Rue De Lyon)","192 (Blvd St Michel)","193 (Blvd St Michel)","194 (Blvd St Michel)","195 (Blvd St Michel)","196 (Blvd St Michel)","197 (Blvd St Michel)","198 (Blvd St Michel)","199 (Blvd St Michel)","200 (Rue La Place)","201 (Rue La Place)","202 (Rue La Place)","203 (Rue La Place)","204 (Rue La Place)","205 (Rue La Place)","206 (Blvd St Michel)","207 (Blvd St Michel)","208 (Blvd St Michel)","209 (Rue Sorbonne)","210 (Rue Sorbonne)","211 (Rue Sorbonne)","212 (Blvd St Michel)","213 (Blvd St Michel)","214 (Blvd St Michel)","215 (Blvd St Michel)","216 (Rue Sorbonne)","217 (Rue Sorbonne)","218 (Rue Sorbonne)","219 (Rue Sorbonne)","220 (Rue Sorbonne)","221 (Rue Sorbonne)","222 (Rue Sorbonne)","223 (Rue Casini)","224 (Rue De Turenne)","225 (Rue De Turenne)","226 (Rue De Turenne)","227 (Rue De Turenne)","228 (Rue De Turenne)","229 (Rue De Turenne)","230 (Rue De Turenne)","231 (Rue De Turenne)","232 (Rue De Turenne)","233 (Rue De Turenne)","234 (Rue De Turenne)","235 (Rue De Turenne)","236 (Rue De Turenne)","237 (Rue Bezout)","238 (Rue Bezout)","239 (Rue Bezout)","240 (Rue de Rennes)","241 (Rue de Rennes)","242 (Rue Monge)","243 (Rue Monge)","244 (Rue Bezout)","245 (Rue Bezout)","246 (Rue Bezout)","247 (Rue Bezout)","248 (Rue Bezout)","249 (Rue de Rennes)","250 (Rue de Rennes)","251 (Rue Monge)","252 (Rue Monge)","253 (Rue Monge)","254 (Rue Monge)","255 (Rue Monge)","256 (Rue Bonaparte)","257 (Rue Bonaparte)","258 (Rue Bonaparte)","259 (Rue Bonaparte)","260 (Rue Bonaparte)","261 (Rue Bonaparte)","262 (Rue Bonaparte)","263 (Rue Bonaparte)","264 (Rue Bonaparte)","265 (Rue Bonaparte)","266 (Rue Pascal)","267 (Rue Pascal)","268 (Rue Pascal)","269 (Rue Pascal)","270 (Rue Pascal)","271 (Rue Pascal)","272 (Rue Pascal)","273 (Rue Pascal)","274 (Rue Pascal)","275 (Rue Sorbonne)","276 (Rue Sorbonne)","277 (Rue Pascal)","278 (Rue Pascal)","279 (Rue Pascal)","280 (Rue Pascal)","281 (Rue Pascal)","282 (Rue Pascal)","283 (Rue Pascal)","284 (Rue Sorbonne)","285 (Rue Sorbonne)","286 (Rue de Turenne)","287 (Rue de Turenne)","288 (Rue de Turenne)","289 (Rue de Turenne)","290 (Rue de Turenne)","291 (Rue de Turenne)","292 (Rue de Turenne)","293 (Rue de Turenne)","294 (Rue de Turenne)","295 (Rue de Turenne)","296 (Rue de Turenne)","297 (Rue de Turenne)","298 (Rue de Turenne)","299 (Rue de Turenne)","300 (Rue de Turenne)","301 (Rue de Turenne)","302 (Rue de Turenne)","303 (Rue de Turenne)","304 (Rue de Turenne)","305 (Rue de Turenne)","306 (Rue de Turenne)","307 (Rue de Turenne)","308 (Rue Casini)","309 (Rue de Turenne)","310 (Rue de Turenne)","311 (Rue de Turenne)","312 (Rue de Turenne)","313 (Rue de Turenne)","314 (Rue de Turenne)"};
    private double[] latitudesArr = new double[]{-26.014614,-26.012674,-26.01441439,-26.01324983,-26.01346316,-26.012659,-26.01452105,-26.01454787,-26.01465122,-26.01464971,-26.01465242,-26.01465423,-26.01465483,-26.01432972,-26.01433304,-26.01433545,-26.01434178,-26.01456203,-26.01456083,-26.01456233,-26.01456233,-26.01459367,-26.01459126,-26.01459246,-26.01459698,-26.01454576,-26.01454697,-26.01454757,-26.01454907,-26.01445115,-26.01444844,-26.01444814,-26.01444693,-26.0144858,-26.0144867,-26.01448369,-26.01448731,-26.0144864,-26.0144852,-26.01448942,-26.0144864,-26.01425349,-26.01425199,-26.01425259,-26.01425319,-26.01432309,-26.01432611,-26.0143255,-26.014324,-26.01433605,-26.01433002,-26.01433304,-26.01433123,-26.01409229,-26.01409259,-26.01411369,-26.01421342,-26.01416521,-26.01408747,-26.01406246,-26.01404649,-26.0140254,-26.01394827,-26.01392235,-26.01384311,-26.01392627,-26.01392175,-26.01384733,-26.01384914,-26.01400431,-26.01397749,-26.01403324,-26.01401546,-26.01402209,-26.01404318,-26.01405764,-26.01376236,-26.01375754,-26.01383648,-26.01384191,-26.01383859,-26.01383799,-26.0137494,-26.0137711,-26.01374127,-26.01368974,-26.01361864,-26.01361653,-26.01359604,-26.01361623,-26.01359061,-26.01355416,-26.0135644,-26.01356892,-26.01346467,-26.01344478,-26.01322091,-26.0132423,-26.01325435,-26.01325526,-26.01326339,-26.0132643,-26.01326972,-26.01327484,-26.01330979,-26.01330889,-26.01322302,-26.01282077,-26.01281896,-26.01281866,-26.01281866,-26.01281986,-26.01302054,-26.01301602,-26.01302536,-26.01305037,-26.0130353,-26.01296841,-26.01291056,-26.01274966,-26.01276683,-26.01279576,-26.01276954,-26.01273098,-26.01270295,-26.01258303,-26.01257851,-26.01258062,-26.01269934,-26.01269542,-26.01272826,-26.01273248,-26.01256224,-26.01253241,-26.01251343,-26.01250077,-26.01242786,-26.0123694,-26.0123694,-26.01230914,-26.01231185,-26.0125068,-26.0125068,-26.01247245,-26.0124384,-26.01237392,-26.01234921,-26.01233204,-26.01233144,-26.01230793,-26.01210093,-26.01213739,-26.01209822,-26.01207592,-26.01212082,-26.01211901,-26.01212293,-26.01202169,-26.01183457,-26.01183427,-26.01189152,-26.01190297,-26.01178485,-26.01181348,-26.01180323,-26.01183879,-26.01183336,-26.01182312,-26.01182493,-26.01184481,-26.0118415,-26.01188489,-26.011887,-26.01209611,-26.01208647,-26.01208345,-26.01207833,-26.01208948,-26.01207863,-26.01203012,-26.01199095,-26.01226816,-26.01219584,-26.0123691,-26.01236217,-26.01233565,-26.01233354,-26.012323,-26.01229287,-26.01222839,-26.01219102,-26.01215035,-26.01212172,-26.01254537,-26.01254567,-26.01239079,-26.01244322,-26.01274845,-26.01270838,-26.012806,-26.0128066,-26.01290031,-26.0129202,-26.01289218,-26.01289218,-26.01278129,-26.0127834,-26.0128057,-26.01280901,-26.01278672,-26.01286205,-26.01304494,-26.01304283,-26.01304042,-26.01336885,-26.01339446,-26.01340079,-26.01323085,-26.01324561,-26.01319831,-26.01320042,-26.01338151,-26.01338663,-26.01339778,-26.01341224,-26.01343514,-26.01348033,-26.01348275,-26.01365931,-26.01394405,-26.01394013,-26.01392416,-26.01391753,-26.01394224,-26.01396333,-26.01396394,-26.01392537,-26.01389915,-26.01385848,-26.01385637,-26.01386932,-26.01386661,-26.01385094,-26.01409079,-26.01416189,-26.01416099,-26.01410344,-26.01413478,-26.01425651,-26.01426856,-26.01426765,-26.01427067,-26.01426645,-26.0143698,-26.01433545,-26.01433063,-26.01434871,-26.01432731,-26.01429146,-26.01429176,-26.01429327,-26.01456022,-26.01456022,-26.01454968,-26.01457499,-26.01456987,-26.01457619,-26.01461084,-26.01457529,-26.01455118,-26.014625,-26.01207622,-26.01208225,-26.01212202,-26.01211871,-26.01210756,-26.0120949,-26.01228654,-26.01228624,-26.01228955,-26.01236789,-26.01236458,-26.0124845,-26.01248691,-26.01248149,-26.01248752,-26.01247486,-26.01249234,-26.01252578,-26.01254778,-26.01254386,-26.01289007,-26.01289549,-26.01288736,-26.01289459,-26.01288796,-26.01288555,-26.01292924,-26.01292803,-26.01310671,-26.01311153,-26.0129666,-26.0132878,-26.01329111,-26.01329051,-26.01329081,-26.01341977,-26.01346798,-26.0135638,-26.01358971,-26.0136328,-26.01367257,-26.01367046,-26.01367317,-26.01377682,-26.01377532,-26.01377923,-26.01378255,-26.01377863,-26.0137717};
    private double[] longitudesArr = new double[]{28.017273,28.014612,28.0179527,28.01471863,28.01676717,28.014677,28.01678091,28.01682014,28.01691167,28.01691402,28.01691737,28.01690932,28.01691771,28.01685166,28.01685501,28.01685669,28.01684227,28.0168939,28.01688686,28.01688686,28.01689357,28.01751047,28.01750444,28.01750612,28.01750477,28.017658,28.017659,28.01766068,28.01767141,28.01796813,28.01797148,28.01796746,28.01797114,28.01798757,28.01798422,28.01798757,28.01798724,28.0179859,28.01798556,28.01798623,28.01798522,28.01701024,28.01701393,28.01701292,28.01701259,28.0178149,28.01781122,28.01781155,28.01781323,28.01781155,28.0178082,28.01781088,28.01781155,28.01735289,28.01734954,28.01747125,28.01788699,28.01806737,28.0181988,28.01825177,28.01773142,28.01776394,28.01796176,28.01794466,28.01802177,28.01751886,28.01752154,28.01757216,28.01757216,28.0166686,28.01668201,28.01675979,28.01680673,28.01691469,28.01703706,28.01712021,28.01667329,28.01667765,28.01705047,28.01704813,28.0170488,28.01705047,28.01754702,28.01782966,28.01795438,28.01806368,28.01657405,28.01663473,28.0166743,28.01701862,28.01726136,28.01735524,28.01741827,28.01759563,28.01784374,28.0178776,28.01659115,28.01663071,28.01679164,28.0167903,28.01691703,28.01692273,28.01717889,28.01718023,28.01764123,28.0176409,28.01784977,28.0167504,28.01674906,28.01675107,28.01675308,28.01675107,28.01700622,28.01701091,28.01724192,28.01735457,28.01739346,28.0175668,28.01767509,28.01704243,28.01706523,28.01727913,28.01733211,28.01740117,28.01748265,28.01735088,28.01735457,28.01735155,28.01658411,28.01662602,28.01669542,28.01674906,28.01653448,28.01654421,28.01665753,28.01671553,28.01692039,28.01706322,28.01707026,28.01714368,28.01714201,28.01621228,28.0162096,28.01631488,28.01635277,28.01640742,28.01645268,28.0165308,28.01657069,28.01662065,28.01640339,28.01642485,28.01645871,28.016509,28.01666658,28.01683255,28.01683456,28.01697236,28.01653985,28.0165432,28.0168882,28.01693413,28.01514711,28.01528793,28.01542807,28.01563494,28.01567987,28.01579922,28.01588338,28.01601078,28.01607717,28.01624916,28.01635344,28.01543579,28.01545255,28.0155481,28.01554576,28.01569965,28.01574826,28.01601481,28.01604632,28.01539052,28.01542271,28.0155243,28.01552195,28.01565539,28.01565975,28.01605202,28.01609293,28.01616032,28.01621798,28.01626794,28.01628269,28.01545121,28.01545188,28.01565774,28.01570904,28.0160309,28.0160765,28.01616132,28.0162143,28.01611438,28.01613584,28.01635679,28.01642284,28.01555213,28.01555112,28.01564064,28.01568087,28.01582202,28.01573887,28.01596418,28.01596418,28.01596485,28.01565673,28.01582236,28.01582906,28.01602017,28.01602989,28.01619384,28.01628739,28.01602419,28.0160252,28.01618077,28.01625822,28.01629107,28.01637255,28.01646341,28.01547099,28.01603526,28.01603694,28.01562656,28.01580828,28.01597625,28.01621027,28.0162086,28.0163246,28.01649794,28.01516857,28.01521618,28.01534493,28.01537845,28.01502407,28.01542606,28.01557492,28.01561013,28.01587332,28.01631555,28.01664144,28.01537778,28.01537443,28.01537275,28.01537611,28.01554374,28.01562488,28.01570065,28.01621027,28.01623575,28.01626459,28.01626258,28.01626157,28.01505692,28.01507134,28.01518433,28.01541265,28.01556353,28.01566545,28.01598497,28.0161174,28.0162143,28.01636584,28.01453993,28.01453792,28.01461067,28.01483028,28.01496539,28.01505122,28.01460464,28.0146043,28.01460128,28.01503915,28.01504385,28.0146499,28.01464956,28.01465359,28.01464923,28.01468946,28.01480547,28.01485408,28.01502675,28.01502809,28.01493555,28.01493958,28.01493857,28.01494427,28.0151042,28.01510487,28.0150881,28.01508944,28.0151109,28.01510956,28.01513571,28.01478099,28.01477797,28.01478066,28.01478133,28.01475853,28.01479675,28.01495768,28.01499221,28.01507033,28.01524971,28.01525306,28.01525239,28.01497009,28.01497076,28.0149664,28.01496573,28.01496439,28.01497042};
    public static ArrayAdapter<String> arrayAdapter;
    public static ArrayList<String> amenitiesNames = new ArrayList<>();
    public static ArrayList<String> units1to99 = new ArrayList<>();
    public static ArrayList<String> units100to199 = new ArrayList<>();
    public static ArrayList<String> units200to314 = new ArrayList<>();
    public static ArrayList<String> tabListArray = new ArrayList<>();
    private static final int NUMBER_OF_AMENITIES = 6;
    private static final int BATCH_99 = 99;
    private static final int BATCH_14 = 14;
    private static int mSectionNumber = 0;
    private static int mPositionOffset = 0;
    static ArrayList<LatLng> placesLatLng = new ArrayList<>();
    static ArrayList<String> placesNames = new ArrayList<>();

    LocationManager locationManager;
    LocationListener locationListener;
    static Location lastKnownLocation;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                lastKnownLocation = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);

            }
        }
    }


    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        amenitiesNames.clear();
        for (int i = 0; i < NUMBER_OF_AMENITIES; i++) {
            amenitiesNames.add(placesNamesArr[i]);
        }

        units1to99.clear();
        for (int i = NUMBER_OF_AMENITIES; i < NUMBER_OF_AMENITIES + BATCH_99 + 1; i++) {
            units1to99.add(placesNamesArr[i]);
        }

        units100to199.clear();
        for (int i = NUMBER_OF_AMENITIES + BATCH_99 ; i < NUMBER_OF_AMENITIES + (BATCH_99 * 2) + 2; i++) {
            units100to199.add(placesNamesArr[i]);
        }

        units200to314.clear();
        for (int i = NUMBER_OF_AMENITIES + (BATCH_99 * 2) + 1; i < NUMBER_OF_AMENITIES + (BATCH_99 * 3) + BATCH_14 + 3; i++) {
            units200to314.add(placesNamesArr[i]);
        }
        units200to314.add("");

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        placesNames.clear();
        placesLatLng.clear();

        for (int i = 0; i < placesNamesArr.length; i++) {
            placesNames.add(placesNamesArr[i]);
            placesLatLng.add(new LatLng(latitudesArr[i], longitudesArr[i]));
        }

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                lastKnownLocation = location;
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            lastKnownLocation = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);

            if (lastKnownLocation == null) {

                Toast.makeText(MainActivity.this,
                        "Could not determine your location. Check if location is enabled or go to Google Maps and then restart LVE Navigator.",
                        Toast.LENGTH_SHORT).show();

            }

        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private Context mContext;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            mContext = getContext();
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            ListView placesListView = (ListView) rootView.findViewById(R.id.placesListView);
            mSectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
            if (mSectionNumber == 1){
                tabListArray = amenitiesNames;
            }else if (mSectionNumber == 2){
                tabListArray = units1to99;
            }else if (mSectionNumber == 3){
                tabListArray = units100to199;
            }else if (mSectionNumber == 4){
                tabListArray = units200to314;
            }
            arrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, tabListArray){
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    TextView item = (TextView) super.getView(position,convertView,parent);
                    item.setTypeface(Typeface.SANS_SERIF);
                    item.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
                    return item;
                }
            };
            placesListView.setAdapter(arrayAdapter);
            placesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mSectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
                    if (mSectionNumber == 1){
                        mPositionOffset = 0;
                    }else if (mSectionNumber == 2){
                        mPositionOffset = NUMBER_OF_AMENITIES;
                    }else if (mSectionNumber == 3){
                        mPositionOffset = NUMBER_OF_AMENITIES + BATCH_99;
                    }else if (mSectionNumber == 4){
                        mPositionOffset = NUMBER_OF_AMENITIES + (BATCH_99 * 2) + 1;
                    }

                    if (lastKnownLocation != null) {

                        Intent intent = new Intent(mContext, MapsActivity.class);
                        intent.putExtra("index", mPositionOffset + position);
                        intent.putExtra("latitude", lastKnownLocation.getLatitude());
                        intent.putExtra("longitude", lastKnownLocation.getLongitude());
                        startActivity(intent);

                    }else{

                        Toast.makeText(mContext, "Could not determine location. Check if location is enabled.", Toast.LENGTH_SHORT).show();

                    }
                    //Toast.makeText(mContext, "Section = " + mSectionNumber + " Position = " + position + " mPositionOffset = " + mPositionOffset, Toast.LENGTH_SHORT).show();
                }
            });
            return rootView;
        }

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }
    }

}