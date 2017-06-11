# Changelog

## 1.0.5 - 01/04/2017
- Deprecate `getSectionPosition(int)` method, method `getPositionInSection(int)` created as replacement.
- Add `getSectionPosition(String)` method.
- Add methods `notifyItemInsertedInSection` and `notifyItemRangeInsertedInSection`.
- Add methods `notifyItemRemovedFromSection`, `notifyItemRangeRemovedFromSection`.
- Add methods `notifyItemChangedInSection` and `notifyItemRangeChangedInSection`.
- Add methods `notifyItemRangeChangedInSection` and `notifyItemMovedInSection`.
- Remove `label` from application tag in the manifest file.

## 1.0.4 - 11/05/2016
- Add `getSectionsMap` method to the adapter.
- Remove `supportsRtl` and `allowBackup` from application tag in the manifest file.

## 1.0.3 - 31/03/2016
- Add `getSectionItemViewType` method to the adapter.

## 1.0.2 - 30/03/2016
- Change access level to public of methods `getSectionPosition` and `getSectionForPosition` of the adapter.

## 1.0.1 - 19/02/2016
- Fix issue when setting section to invisible.