INSERT INTO afprefix (id, name) VALUES ('PREFIX_REQUEST', 'RIS');
INSERT INTO afprefix(id, name) VALUES ('PREFIX_TRANSACTION', 'IRAF');

INSERT INTO afstate (id, name) VALUES ('STATE_ISSUED', 'ISSUED');
INSERT INTO afstate (id, name) VALUES ('STATE_OPEN', 'OPEN');

INSERT INTO aftransactiontype (id, name) VALUES ('TYPE_ISSUANCE', 'ISSUANCE');
INSERT INTO aftransactiontype (id, name) VALUES ('TYPE_PURCHASE', 'PURCHASE');

INSERT INTO aftransactionstatus (id, name) VALUES ('STATUS_PENDING', 'PENDING');
INSERT INTO aftransactionstatus (id, name) VALUES ('STATUS_CANCELLED', 'CANCELLED');
INSERT INTO aftransactionstatus (id, name) VALUES ('STATUS_COMPLETED', 'COMPLETED');


INSERT INTO aftype (id, title, seriesLength, unit, quantity) VALUES ('51', 'ACCOUNTABLE FORM', 7, 'STUB', 1);
