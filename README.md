# ANE-Samsung-In-App-Purchase
==================================

### General info :
- Samsung In-App Purchase SDK Version : 4.0.1Sep 23, 2016. 
- Add this to your android manifest :

```xml
<android>
	<manifestAdditions><![CDATA[
		<manifest android:installLocation="auto">

			<!-- ************************************************** -->
			<!-- START samsung iap app purchase permissions -->

			<uses-permission android:name="com.sec.android.app.billing.permission.BILLING"/>
			<uses-permission android:name="android.permission.INTERNET"/>

			<!-- END samsung iap app purchase permissions -->
			<!-- ************************************************** -->

			<application android:enabled="true">
			
				...
			
				<!-- ************************************************** -->
				<!-- START samsung iap app purchase activities -->

				<activity
					android:name="sk.krusty.ane.samsung.inapppurchase.activities.InitializeActivity"
					android:theme="@style/Theme.Empty"
					android:configChanges="orientation|screenSize"/>
				<activity
					android:name="sk.krusty.ane.samsung.inapppurchase.activities.StartPaymentActivity"
					android:theme="@style/Theme.Empty"
					android:configChanges="orientation|screenSize"/>
				<activity
					android:name="sk.krusty.ane.samsung.inapppurchase.activities.GetItemsActivity"
					android:theme="@style/Theme.Empty"
					android:configChanges="orientation|screenSize"/>
				<activity
					android:name="sk.krusty.ane.samsung.inapppurchase.activities.GetItemsInboxActivity"
					android:theme="@style/Theme.Empty"
					android:configChanges="orientation|screenSize"/>


				<activity
					android:name="com.samsung.android.sdk.iap.lib.activity.InboxActivity"
					android:theme="@style/Theme.Empty"
					android:configChanges="orientation|screenSize"/>

				<activity
					android:name="com.samsung.android.sdk.iap.lib.activity.PaymentActivity"
					android:theme="@style/Theme.Empty"
					android:configChanges="orientation|screenSize"/>

				<activity
					android:name="com.samsung.android.sdk.iap.lib.activity.ItemActivity"
					android:theme="@style/Theme.Empty"
					android:configChanges="orientation|screenSize"/>


				<!-- END samsung iap app purchase activities -->
				<!-- ************************************************** -->
				
			</application>
		</manifest>
	]]></manifestAdditions>
</android>
```

### Referecies :
- http://developer.samsung.com/iap
- http://developer.samsung.com/iap/releases

When initializing your app, call the initialize method to init the internal ANE.
