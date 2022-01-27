-- test on_change_comment_tg

UPDATE COMMENTS SET COMMENT_TEXT = 'spoko' WHERE COMMENT_ID = 4;
commit;

-- test on_change_offer_tg

UPDATE OFFERS SET PRICE = 5 WHERE OFFER_ID = 9;
commit;

