CREATE OR REPLACE TRIGGER on_change_comment_tg
BEFORE UPDATE ON COMMENTS
FOR EACH ROW
DECLARE
    new_index NUMBER;
BEGIN
    SELECT max(c.COMMENT_CHANGE_ID) INTO new_index FROM COMMENTS_HISTORY c;
    IF new_index IS NULL THEN
        new_index := 1;
    ELSE
        new_index := new_index + 1;
    END IF;
    INSERT INTO COMMENTS_HISTORY VALUES (new_index, :old.COMMENT_ID, :old.OFFER_ID,
                                         :old.USER_LOGIN, :old.COMMENT_TEXT,
                                         CURRENT_DATE);
end;



CREATE OR REPLACE TRIGGER on_change_offer_tg
BEFORE UPDATE ON OFFERS
FOR EACH ROW
DECLARE
    new_index NUMBER;
BEGIN
    SELECT max(o.OFFER_CHANGE_ID) INTO new_index FROM OFFERS_HISTORY o;
    SELECT max(c.COMMENT_CHANGE_ID) INTO new_index FROM COMMENTS_HISTORY c;
    IF new_index IS NULL THEN
        new_index := 1;
    ELSE
        new_index := new_index + 1;
    END IF;
    INSERT INTO OFFERS_HISTORY VALUES (new_index, :old.OFFER_ID, :old.NAME,
                                       :old.DESCRIPTION, :old.CATEGORY,
                                        :old.FOR_EXCHANGE, :old.FOR_SALE,
                                         :old.PRICE, :old.SELLER, :old.OFFER_STATUS,
                                          :old.PHOTO, :old.LOCALISATION,
                                           CURRENT_DATE);
end;
