import random
import csv


entities = ['qwe', 'ewq', 'oil', 'gold', 'power', 'usd']
dirs = ['B','S']
months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul']
cur = ['AED','AFN','ALL','AMD','ANG','AOA','ARS','AUD','AWG','AZN','BAM','BBD','BDT','BGN','BHD','BIF','BMD','BND','BOB','BOV','BRL','BSD','BTN','BWP','BYN','BYR','BZD','CAD','CDF','CHE','CHF','CHW','CLF','CLP','CNY','COP','COU','CRC','CUC','CUP','CVE','CZK','DJF','DKK','DOP','DZD','EGP','ERN','ETB','EUR','FJD','FKP','GBP','GEL','GHS','GIP','GMD','GNF','GTQ','GYD','HKD','HNL','HRK','HTG','HUF','IDR','ILS','INR','IQD','IRR','ISK','JMD','JOD','JPY','KES','KGS','KHR','KMF','KPW','KRW','KWD','KYD','KZT','LAK','LBP','LKR','LRD','LSL','LYD','MAD','MDL','MGA','MKD','MMK','MNT','MOP','MRO','MUR','MVR','MWK','MXN','MXV','MYR','MZN','NAD','NGN','NIO','NOK','NPR','NZD','OMR','PAB','PEN','PGK','PHP','PKR','PLN','PYG','QAR','RON','RSD','RUB','RWF','SAR','SBD','SCR','SDG','SEK','SGD','SHP','SLL','SOS','SRD','SSP','STD','SVC','SYP','SZL','THB','TJS','TMT','TND','TOP','TRY','TTD','TWD','TZS','UAH','UGX','USD','USN','UYI','UYU','UZS','VEF','VND','VUV','WST','XAF','XAG','XAU','XBA','XBB','XBC','XBD','XCD','XDR','XOF','XPD','XPF','XPT','XSU','XTS','XUA','XXX','YER','ZAR','ZMW','ZWL']

with open('generated.csv', 'w', newline='') as csvfile:
	file = csv.writer(csvfile, delimiter=',',
							quotechar='|', quoting=csv.QUOTE_MINIMAL)

	file.writerow(['Entity','Buy/Sell','AgreedFx','Currency','InstructionDate','SettlementDate','Units','Price per unit'])
	for i in range(0,1000):
		v= [
			random.choice(entities),
			random.choice(dirs),
			random.uniform(0.5, 100),
			random.choice(cur),
			"{}-{}-2016".format(random.randint(1,27), random.choice(months)),
			"{}-{}-2016".format(random.randint(1,27), random.choice(months)),
			random.uniform(0.5, 500),
			random.uniform(0.5, 200)
		]

		file.writerow(v)


