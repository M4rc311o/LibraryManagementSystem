ALTER TABLE bds.physical_book
RENAME COLUMN "state" to "condition";

ALTER TABLE bds.loaned_book
ADD COLUMN fee numeric(5, 2);

UPDATE bds.loaned_book SET fee = 2.54 WHERE loaned_book_id = 1;
UPDATE bds.loaned_book SET fee = 5.00 WHERE loaned_book_id = 2;
UPDATE bds.loaned_book SET fee = 0.30 WHERE loaned_book_id = 4;
UPDATE bds.loaned_book SET fee = 1.00 WHERE loaned_book_id = 5;
UPDATE bds.loaned_book SET fee = 0.50 WHERE loaned_book_id = 6;
UPDATE bds.loaned_book SET fee = 6.00 WHERE loaned_book_id = 8;
UPDATE bds.loaned_book SET fee = 3.34 WHERE loaned_book_id = 10;
UPDATE bds.loaned_book SET fee = 3.45 WHERE loaned_book_id = 11;
