import pandas as pd

XLSX_FILE = 'trading-statistics-mar-2019.xlsx'
SHEET_NAME = 'At Contract Level'
ENGINE = 'openpyxl'

def isTransactionDoneForLeastMinExpiry(row, min_expiry_date, second_min_expiry_date):
    if row[transaction_date] <= min_expiry_date:
        expiry_date_to_check_against = min_expiry_date
    else:
        expiry_date_to_check_against = second_min_expiry_date

    return row[expiry_date_column] == expiry_date_to_check_against

def isTrue():
    return True

if __name__ == "__main__":
    df = pd.read_excel(XLSX_FILE, sheet_name=SHEET_NAME, engine=ENGINE, header=None)

    df.columns = (df.loc[0:3].fillna('').apply(' '.join).str.strip())
    df = df.loc[4:].reset_index(drop=True)

    instrument_type_column_name = df.columns[4]
    symbol = df.columns[6]
    transaction_date = df.columns[2]
    expiry_date_column = df.columns[7]

    print(df.shape)
    df.drop(df[(df[instrument_type_column_name] != 'FUTCOM') | (df[symbol] != 'SILVER')].index, inplace = True)

    print(df.shape)

    minExpiryDate = df[expiry_date_column].min()
    secondMinExpiryDate = (df[df[expiry_date_column] != minExpiryDate][expiry_date_column].min())

    print ("First Expiry: " , minExpiryDate)
    print ("Second Expiry: " , secondMinExpiryDate)

    print(df.loc[:, [transaction_date, expiry_date_column]])

    for index, row in df.iterrows():
        if isTransactionDoneForLeastMinExpiry(row, minExpiryDate, secondMinExpiryDate) == False:
            df.drop(index, inplace=True)

    print(df.loc[:, [transaction_date, expiry_date_column]])

    df.to_excel("output-trading-statistics-mar-2019.xlsx",
             sheet_name=SHEET_NAME)