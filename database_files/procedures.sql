create or replace procedure offers_buyer(cur_offer_id NUMBER)
AS
    buyer   varchar2(45);
BEGIN
    buyer := null;
    for trans in (select O.* , T.buyer, T.status from transactions T
                                                          join offers O on(O.offer_id = T.offer_id)
                  where O.offer_id = cur_offer_id)
        LOOP
            if trans.status = 1 then
                buyer := trans.buyer;
            end if;
        END LOOP;
    if buyer is null then
        DBMS_OUTPUT.PUT_LINE('Item is not sold');
    else
        DBMS_OUTPUT.PUT_LINE(buyer);
    end if;
END;

--------------------------------------------------------------------------------

create or replace procedure transactions_with_status(sta NUMBER, login VARCHAR2)
as
    cursor cr is select * from TRANSACTIONS join OFFERS using(OFFER_ID);
    tra_off_row cr%rowtype;
begin
    open cr;
    loop
        exit when cr%NOTFOUND;
        fetch cr into tra_off_row;
        if tra_off_row.SELLER like login and tra_off_row.STATUS=sta
        then
            DBMS_OUTPUT.PUT_LINE('Seller: ' || tra_off_row.SELLER || ' | Buyer: ' || tra_off_row.BUYER ||
                                 ' | Product: ' || tra_off_row.NAME || ' | Price: ' || tra_off_row.PRICE ||
                                 ' | Status: ' || tra_off_row.STATUS);
        end if;
    end loop;
    close cr;
end;