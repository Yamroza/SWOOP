CREATE SEQUENCE comment_history_pk START WITH 1 maxvalue 2000;


CREATE OR REPLACE TRIGGER on_change_comment_tg
BEFORE UPDATE ON COMMENTS
FOR EACH ROW
DECLARE
    new_index NUMBER;
BEGIN
    SELECT comment_history_pk.nextval INTO new_index FROM DUAL;
    INSERT INTO COMMENTS_HISTORY VALUES (new_index, :old.COMMENT_ID, :old.OFFER_ID,
                                         :old.USER_LOGIN, :old.COMMENT_TEXT,
                                         CURRENT_DATE);
end;

CREATE SEQUENCE offer_history_pk START WITH 1 maxvalue 2000;

CREATE OR REPLACE TRIGGER on_change_offer_tg
BEFORE UPDATE ON OFFERS
FOR EACH ROW
DECLARE
    new_index NUMBER;
BEGIN
    SELECT offer_history_pk.nextval INTO new_index FROM DUAL;
    INSERT INTO OFFERS_HISTORY VALUES (new_index, :old.OFFER_ID, :old.NAME,
                                       :old.DESCRIPTION, :old.CATEGORY,
                                        :old.FOR_EXCHANGE, :old.FOR_SALE,
                                         :old.PRICE, :old.SELLER, :old.OFFER_STATUS,
                                          :old.PHOTO, :old.LOCALISATION,
                                           CURRENT_DATE);
end;
